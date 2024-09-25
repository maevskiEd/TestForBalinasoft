package ed.maevski.testbalinasoft.view.base

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BaseViewPagerAdapter(fragment: Fragment, private val fragmentsList: Array<Fragment>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragmentsList.size

    override fun createFragment(position: Int): Fragment = fragmentsList[position]
}