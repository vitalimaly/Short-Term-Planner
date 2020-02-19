package com.vitaliimalone.simpletodo.presentation.screens.home.common

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vitaliimalone.simpletodo.presentation.screens.hometab.HomeTabFragment

class HomeTabFragmentAdapter(
    private val fragments: Array<HomeTab>, fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int) = HomeTabFragment.newInstance(fragments[position])

    override fun getItemCount() = fragments.size
}