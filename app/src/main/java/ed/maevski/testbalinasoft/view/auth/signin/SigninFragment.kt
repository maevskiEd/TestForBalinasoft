package ed.maevski.testbalinasoft.view.auth.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import ed.maevski.testbalinasoft.databinding.FragmentSigninBinding
import ed.maevski.testbalinasoft.domain.models.User
import ed.maevski.testbalinasoft.view.MainActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class SigninFragment : Fragment() {
    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SigninViewModel

    @Inject
    lateinit var vmFactory: SigninViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[SigninViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isSubmit.collect {
                if (it) {
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity?.finish()
                } else {
                    Snackbar.make(
                        binding.root,
                        "Ошибка авторизации.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        btnLogin.setOnClickListener {
            viewModel.signin(User(login = binding.etLogin.text.toString(), password = binding.etPassword.text.toString()))
        }
    }

    companion object {
        fun newInstance() = SigninFragment()
    }
}