package com.vitaliimalone.simpletodo.presentation.hometab

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.hometab.common.TaskTouchHelperCallback
import com.vitaliimalone.simpletodo.presentation.hometab.common.TasksAdapter
import com.vitaliimalone.simpletodo.presentation.models.HomeTab
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.setTextColor
import com.vitaliimalone.simpletodo.presentation.views.DefaultDividerItemDecoration
import kotlinx.android.synthetic.main.tasks_pager_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeTabFragment : BaseFragment(R.layout.tasks_pager_item) {
    companion object {
        private const val ARG_HOME_TAB = "arg_home_tab"

        fun newInstance(homeTab: HomeTab): HomeTabFragment {
            return HomeTabFragment().apply {
                arguments = bundleOf(ARG_HOME_TAB to homeTab.name)
            }
        }
    }

    private val viewModel: HomeTabViewModel by viewModel()
    private val tasksAdapter by lazy { TasksAdapter() }
    private val homeTab by lazy {
        HomeTab.valueOf(requireArguments().getString(ARG_HOME_TAB)!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchTasksForHomeTab(homeTab)
        setupClickListeners()
        setupViews()
        setupObservers()
    }

    private fun setupClickListeners() {

    }

    private fun setupViews() {
        tasksPagerRecyclerView.adapter = tasksAdapter
        tasksPagerRecyclerView.addItemDecoration(
            DefaultDividerItemDecoration(
                requireContext(),
                marginLeft = Res.dimen(R.dimen.home_divider_margin),
                marginRight = Res.dimen(R.dimen.home_divider_margin)))
        val itemTouchHelper = ItemTouchHelper(
            TaskTouchHelperCallback(
                this::onTabSwipe,
                getSwipedToTabText(homeTab, ItemTouchHelper.LEFT),
                getSwipedToTabText(homeTab, ItemTouchHelper.RIGHT),
                getSwipedToTabColor(homeTab, ItemTouchHelper.LEFT),
                getSwipedToTabColor(homeTab, ItemTouchHelper.RIGHT)
            )
        )
        itemTouchHelper.attachToRecyclerView(tasksPagerRecyclerView)
    }

    private fun onTabSwipe(position: Int, direction: Int) {
        val swipedTask = tasksAdapter.tasks[position]
        if (direction == ItemTouchHelper.LEFT) {
            viewModel.onSwipeLeft(homeTab, swipedTask)
        } else if (direction == ItemTouchHelper.RIGHT) {
            viewModel.onSwipeRight(homeTab, swipedTask)
        }
        val snackbarTitleColor = Res.color(R.color.primary_white_text_color)
        val snackbarTitle =
            Res.string(R.string.snackbar_task_swiped, getSwipedToTabText(homeTab, direction))
                .setTextColor(snackbarTitleColor)
        val swipedSnackbar = Snackbar.make(
            tasksPagerRecyclerView,
            snackbarTitle,
            Snackbar.LENGTH_LONG
        )
        swipedSnackbar.setAction(Res.string(R.string.snackbar_undo)) {
            viewModel.undoSwipe()
        }
        swipedSnackbar.show()
    }

    private fun getSwipedToTabText(currentTab: HomeTab, direction: Int): String {
        return if (direction == ItemTouchHelper.LEFT) {
            if (currentTab == HomeTab.values().first()) {
                Res.string(R.string.archive)
            } else {
                HomeTab.values()[currentTab.ordinal - 1].title
            }
        } else if (direction == ItemTouchHelper.RIGHT) {
            if (currentTab == HomeTab.values().last()) {
                Res.string(R.string.archive)
            } else {
                HomeTab.values()[currentTab.ordinal + 1].title
            }
        } else {
            throw IllegalArgumentException("Should be LEFT or RIGHT")
        }
    }

    private fun getSwipedToTabColor(currentTab: HomeTab, direction: Int): Int {
        return if (direction == ItemTouchHelper.LEFT) {
            if (currentTab == HomeTab.values().first()) {
                Res.color(R.color.pale_red)
            } else {
                Res.color(R.color.pale_background_dark_color)
            }
        } else if (direction == ItemTouchHelper.RIGHT) {
            if (currentTab == HomeTab.values().last()) {
                Res.color(R.color.pale_red)
            } else {
                Res.color(R.color.pale_background_dark_color)
            }
        } else {
            throw IllegalArgumentException("Should be LEFT or RIGHT")
        }
    }

    private fun setupObservers() {
        viewModel.tasksForHomeTab.observe(viewLifecycleOwner, Observer {
            tasksAdapter.tasks = it
        })
    }
}