package com.vitaliimalone.simpletodo.presentation.home

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.home.common.HomeTabFragmentAdapter
import com.vitaliimalone.simpletodo.presentation.models.HomeTab
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.Dialogs
import com.vitaliimalone.simpletodo.presentation.utils.hideKeyboard
import com.vitaliimalone.simpletodo.presentation.utils.showKeyboard
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.home_fragment) {
    private val viewModel: HomeViewModel by viewModel()
    private val tasksPagerAdapter by lazy {
        HomeTabFragmentAdapter(listOf(
                HomeTab.TODAY, HomeTab.WEEK, HomeTab.MONTH, HomeTab.TODO), this)
    }
    private var oldPagePosition = 0
    private val animationTime = 100L

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupViews()
        setupObservers()
    }

    private fun setupClickListeners() {
        addFab.setOnClickListener { view ->
            showKeyboard(view)
            val currentTab = HomeTab.values()[tasksViewPager.currentItem]
            val defaultDate = DateTimeUtils.getDateForAddNewTask(currentTab)
            Dialogs.showAddNewTaskDialog(requireContext(), defaultDate) {
                viewModel.addNewTask(it)
                hideKeyboard()
            }
        }
    }

    private fun setupViews() {
        tasksViewPager.isUserInputEnabled = false
        tasksViewPager.adapter = tasksPagerAdapter
        TabLayoutMediator(tabsTabLayout, tasksViewPager) { tab, position ->
            tab.text = HomeTab.values()[position].title
        }.attach()
        tasksViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                animatePageChange(position)
                oldPagePosition = position
            }
        })
    }

    private fun animatePageChange(position: Int) {
        val height = dateRangeTextView.height.toFloat()
        val moveUp = oldPagePosition < position
        val endAction = {
            dateRangeTextView.text = DateTimeUtils.getDateForTab(HomeTab.values()[position])
            dateRangeTextView.translationY = if (moveUp) height else -height
            dateRangeTextView.animate()
                    .setDuration(animationTime / 2)
                    .translationY(0f)
                    .alpha(1f)
                    .start()
        }
        dateRangeTextView.animate()
                .setDuration(animationTime / 2)
                .translationY(if (moveUp) -height else height)
                .alpha(0f)
                .withEndAction(endAction)
                .start()
    }

    private fun setupObservers() {
    }

}
