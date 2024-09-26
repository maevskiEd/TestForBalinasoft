package ed.maevski.testbalinasoft.view.auth.signup

import android.content.Context
import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import ed.maevski.testbalinasoft.R
import ed.maevski.testbalinasoft.databinding.FragmentSigninBinding
import ed.maevski.testbalinasoft.databinding.FragmentSignupBinding
import ed.maevski.testbalinasoft.domain.models.User
import ed.maevski.testbalinasoft.view.MainActivity
import ed.maevski.testbalinasoft.view.auth.signin.SigninViewModel
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
            println("AuthFragment: запуск authFragmentViewModel.isEntry outside")
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
            viewModel.signup(User(binding.etLogin.text.toString(), binding.etPassword.text.toString()))
        }
    }

    companion object {
        fun newInstance() = SignupFragment()
    }
}