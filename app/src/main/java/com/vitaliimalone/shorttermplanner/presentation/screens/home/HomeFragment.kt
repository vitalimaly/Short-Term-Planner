package com.vitaliimalone.shorttermplanner.presentation.screens.home

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.base.BaseFragment
import com.vitaliimalone.shorttermplanner.presentation.screens.home.common.HomeTab
import com.vitaliimalone.shorttermplanner.presentation.screens.home.common.HomeTabFragmentAdapter
import com.vitaliimalone.shorttermplanner.presentation.utils.DateTimeUtils
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
        addFab.setOnClickListener {
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
        dateRangeTextView.addTextChangedListener { text ->
            if (!text.isNullOrBlank() && dateRangeTextView.lineCount > 1
                && text.contains(DateTimeUtils.TAB_START_END_DATE_SEPARATOR) && !text.contains("\n")
            ) {
                val indexOf = text.indexOf(DateTimeUtils.TAB_START_END_DATE_SEPARATOR) + 1
                text.replace(indexOf, indexOf + 1, "\n")
            }
        }
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
