package ed.maevski.testbalinasoft.view.auth.signup

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
import ed.maevski.testbalinasoft.databinding.FragmentSignupBinding
import ed.maevski.testbalinasoft.domain.models.User
import ed.maevski.testbalinasoft.view.MainActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SignupViewModel

    @Inject
    lateinit var vmFactory: SignupViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, vmFactory)[SignupViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isSubmit.collect {
                if (it) {
                    startActivity(Intent(activity, MainActivity::class.java))
                    activity?.finish()
                } else {
                    Snackbar.make(
                        binding.root,
                        "Ошибка регистрации.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
        initBtnListeners()
    }

    private fun initBtnListeners() = with(binding) {
        btnLogin.setOnClickListener {
            viewModel.signup(User(login = binding.etLogin.text.toString(), password =  binding.etPassword.text.toString()))
        }
    }

    companion object {
        fun newInstance() = SignupFragment()
    }
}