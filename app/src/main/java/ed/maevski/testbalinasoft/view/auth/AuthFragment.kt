package ed.maevski.testbalinasoft.view.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import ed.maevski.testbalinasoft.R
import ed.maevski.testbalinasoft.databinding.FragmentAuthBinding
import ed.maevski.testbalinasoft.view.auth.signin.SigninFragment
import ed.maevski.testbalinasoft.view.auth.signup.SignupFragment
import ed.maevski.testbalinasoft.view.base.BaseViewPagerAdapter

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel

//    @Inject
//    lateinit var vmFactory: AuthViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel =
//            ViewModelProvider(this, vmFactory)[AuthViewModel::class.java]

        addViewPager()
    }

    private fun addViewPager() {
        binding.viewPager.adapter = BaseViewPagerAdapter(
            this, arrayOf(
                SigninFragment.newInstance(),
                SignupFragment.newInstance()
            )
        )

        binding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabsAuth, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.login)
                }

                1 -> {
                    tab.text = getString(R.string.reg)
                }
            }
        }.attach()
    }
}