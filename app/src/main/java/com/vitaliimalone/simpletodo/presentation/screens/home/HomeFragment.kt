package com.vitaliimalone.simpletodo.presentation.screens.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.screens.home.common.HomeTab
import com.vitaliimalone.simpletodo.presentation.screens.home.common.HomeTabFragmentAdapter
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.extensions.showKeyboard
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.home_fragment) {
    private val viewModel: HomeViewModel by viewModel()
    private var oldPagePosition = -1
    private val animationTime = 100L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkRateDialog()
    }

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
            val action = HomeFragmentDirections.actionHomeFragmentToAddNewTaskDialog(currentTab)
            findNavController().navigate(action)
        }
        settingsImageView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupViews() {
        tasksViewPager.isUserInputEnabled = false
        tasksViewPager.adapter = HomeTabFragmentAdapter(HomeTab.values(), this)
        TabLayoutMediator(tabsTabLayout, tasksViewPager) { tab, position ->
            tab.text = HomeTab.values()[position].getTitle()
        }.attach()
        tasksViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (oldPagePosition != position) {
                    animatePageChange(position)
                    oldPagePosition = position
                }
            }
        })
        dateRangeTextView.text =
            DateTimeUtils.getTabStartEndDateText(HomeTab.values()[tasksViewPager.currentItem])
    }

    private fun animatePageChange(position: Int) {
        val height = dateRangeTextView.height.toFloat()
        val moveUp = oldPagePosition < position
        val endAction = {
            dateRangeTextView.text = DateTimeUtils.getTabStartEndDateText(HomeTab.values()[position])
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
        viewModel.showRateDialogEvent.observe(viewLifecycleOwner, Observer {
            val action = HomeFragmentDirections.actionHomeFragmentToRateDialog()
            findNavController().navigate(action)
        })
    }
}
