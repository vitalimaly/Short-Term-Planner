package com.vitaliimalone.simpletodo.presentation.hometab

import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.home.HomeFragmentDirections
import com.vitaliimalone.simpletodo.presentation.home.common.HomeTab
import com.vitaliimalone.simpletodo.presentation.hometab.common.TaskTouchHelperCallback
import com.vitaliimalone.simpletodo.presentation.hometab.common.TasksAdapter
import com.vitaliimalone.simpletodo.presentation.popups.duedatepopup.DueDatePopup
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.extensions.setTextColor
import com.vitaliimalone.simpletodo.presentation.views.DefaultDividerItemDecoration
import kotlinx.android.synthetic.main.tasks_pager_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeTabFragment : BaseFragment(R.layout.tasks_pager_item) {
    companion object {
        private const val ARG_HOME_TAB_TYPE = "arg_home_tab"

        fun newInstance(homeTab: HomeTab) = HomeTabFragment().apply {
            arguments = bundleOf(ARG_HOME_TAB_TYPE to homeTab)
        }
    }

    private val viewModel: HomeTabViewModel by viewModel()
    private val tasksAdapter by lazy { TasksAdapter(::onTaskClick, ::onTaskLongClick) }
    private val homeTabType by lazy { requireArguments().getSerializable(ARG_HOME_TAB_TYPE) as HomeTab }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getTasksForHomeTab(homeTabType).observe(viewLifecycleOwner, Observer {
            tasksAdapter.tasks = it
        })
    }

    private fun setupClickListeners() {
    }

    private fun onTaskClick(task: Task) {
        val action = HomeFragmentDirections.actionHomeFragmentToTaskDetailsFragment(task)
        findNavController().navigate(action)
    }

    private fun onTaskLongClick(task: Task, coordinates: Point) {
        DueDatePopup(requireContext(), childFragmentManager, task.dueTo) { pickedDate ->
            viewModel.updateTaskDueDate(task, pickedDate)
        }.run {
            showAtLocation(requireView(), Gravity.NO_GRAVITY, coordinates.x, coordinates.y)
        }
    }

    private fun setupViews() {
        tasksPagerRecyclerView.adapter = tasksAdapter
        tasksPagerRecyclerView.addItemDecoration(
            DefaultDividerItemDecoration(
                requireContext(),
                marginLeft = Res.dimen(requireContext(), R.dimen.home_divider_margin),
                marginRight = Res.dimen(requireContext(), R.dimen.home_divider_margin)
            )
        )
        val itemTouchHelper = ItemTouchHelper(
            TaskTouchHelperCallback(
                requireContext(),
                this::onTabSwipe,
                getSwipedToTabText(homeTabType, ItemTouchHelper.LEFT),
                getSwipedToTabText(homeTabType, ItemTouchHelper.RIGHT),
                getSwipedToTabColor(homeTabType, ItemTouchHelper.LEFT),
                getSwipedToTabColor(homeTabType, ItemTouchHelper.RIGHT)
            )
        )
        itemTouchHelper.attachToRecyclerView(tasksPagerRecyclerView)
    }

    private fun onTabSwipe(position: Int, direction: Int) {
        val swipedTask = tasksAdapter.tasks[position]
        if (direction == ItemTouchHelper.LEFT) {
            viewModel.onSwipeLeft(homeTabType, swipedTask)
        } else if (direction == ItemTouchHelper.RIGHT) {
            viewModel.onSwipeRight(homeTabType, swipedTask)
        }
        val snackbarTitleColor = Res.color(requireContext(), R.attr.themeTextColorPrimaryInverse)
        val snackbarTitle =
            Res.string(R.string.snackbar_task_swiped, getSwipedToTabText(homeTabType, direction))
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
                HomeTab.values()[currentTab.ordinal - 1].getTitle()
            }
        } else if (direction == ItemTouchHelper.RIGHT) {
            if (currentTab == HomeTab.values().last()) {
                Res.string(R.string.archive)
            } else {
                HomeTab.values()[currentTab.ordinal + 1].getTitle()
            }
        } else {
            throw IllegalArgumentException("Should be LEFT or RIGHT")
        }
    }

    private fun getSwipedToTabColor(currentTab: HomeTab, direction: Int): Int {
        return if (direction == ItemTouchHelper.LEFT) {
            if (currentTab == HomeTab.values().first()) {
                Res.color(requireContext(), R.attr.themeColorError)
            } else {
                Res.color(requireContext(), R.attr.themeColorBackgroundSecondary)
            }
        } else if (direction == ItemTouchHelper.RIGHT) {
            if (currentTab == HomeTab.values().last()) {
                Res.color(requireContext(), R.attr.themeColorError)
            } else {
                Res.color(requireContext(), R.attr.themeColorBackgroundSecondary)
            }
        } else {
            throw IllegalArgumentException("Should be LEFT or RIGHT")
        }
    }
}