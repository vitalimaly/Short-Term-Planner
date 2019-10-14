package com.vitaliimalone.simpletodo.presentation.home

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.home.common.HomeTabs
import com.vitaliimalone.simpletodo.presentation.home.common.TasksPagerAdapter
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.forceShowKeyboard
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.home_fragment) {
    private val viewModel: HomeViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupViews()
    }

    private fun setupClickListeners() {
        addFab.setOnClickListener {
            forceShowKeyboard()
        }
    }

    private fun setupViews() {
        tasksViewPager.isUserInputEnabled = false
        tasksViewPager.adapter = TasksPagerAdapter()
        TabLayoutMediator(tabsTabLayout, tasksViewPager) { tab, position ->
            tab.text = getString(HomeTabs.values()[position].title)
        }.attach()
        dateRangeTextView.text = DateTimeUtils.getTodayDayOfWeekAndMonthAndDayFull()
    }
}
