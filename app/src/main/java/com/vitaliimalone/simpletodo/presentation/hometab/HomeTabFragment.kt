package com.vitaliimalone.simpletodo.presentation.hometab

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.home.HomeFragmentDirections
import com.vitaliimalone.simpletodo.presentation.hometab.common.TaskTouchHelperCallback
import com.vitaliimalone.simpletodo.presentation.hometab.common.TasksAdapter
import com.vitaliimalone.simpletodo.presentation.utils.HomeTabType
import com.vitaliimalone.simpletodo.presentation.utils.HomeTabs
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.setTextColor
import com.vitaliimalone.simpletodo.presentation.views.DefaultDividerItemDecoration
import kotlinx.android.synthetic.main.tasks_pager_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeTabFragment : BaseFragment(R.layout.tasks_pager_item) {
    companion object {
        private const val ARG_HOME_TAB_TYPE = "arg_home_tab"

        fun newInstance(homeTabType: HomeTabType): HomeTabFragment {
            return HomeTabFragment().apply {
                arguments = bundleOf(ARG_HOME_TAB_TYPE to homeTabType.name)
            }
        }
    }

    private val viewModel: HomeTabViewModel by viewModel()
    private val tasksAdapter by lazy { TasksAdapter(::onTaskClicked) }
    private val homeTabType by lazy {
        HomeTabType.valueOf(requireArguments().getString(ARG_HOME_TAB_TYPE)!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchTasksForHomeTab(homeTabType)
        setupClickListeners()
        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.tasksForHomeTab.observe(viewLifecycleOwner, Observer {
            tasksAdapter.tasks = it
        })
    }

    private fun setupClickListeners() {
    }

    private fun onTaskClicked(task: Task) {
        val action = HomeFragmentDirections.actionHomeFragmentToTaskDetailsFragment(task)
        findNavController().navigate(action)
    }

    private fun setupViews() {
        tasksPagerRecyclerView.adapter = tasksAdapter
        tasksPagerRecyclerView.addItemDecoration(
            DefaultDividerItemDecoration(
                requireContext(),
                marginLeft = Res.dimen(R.dimen.home_divider_margin),
                marginRight = Res.dimen(R.dimen.home_divider_margin)
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

    private fun getSwipedToTabText(currentTab: HomeTabType, direction: Int): String {
        return if (direction == ItemTouchHelper.LEFT) {
            if (currentTab == HomeTabType.values().first()) {
                Res.string(R.string.archive)
            } else {
                HomeTabs.homeTabFragments[currentTab.ordinal - 1].title
            }
        } else if (direction == ItemTouchHelper.RIGHT) {
            if (currentTab == HomeTabType.values().last()) {
                Res.string(R.string.archive)
            } else {
                HomeTabs.homeTabFragments[currentTab.ordinal + 1].title
            }
        } else {
            throw IllegalArgumentException("Should be LEFT or RIGHT")
        }
    }

    private fun getSwipedToTabColor(currentTab: HomeTabType, direction: Int): Int {
        return if (direction == ItemTouchHelper.LEFT) {
            if (currentTab == HomeTabType.values().first()) {
                Res.color(requireContext(), R.attr.themeColorError)
            } else {
                Res.color(requireContext(), R.attr.themeColorBackgroundSecondary)
            }
        } else if (direction == ItemTouchHelper.RIGHT) {
            if (currentTab == HomeTabType.values().last()) {
                Res.color(requireContext(), R.attr.themeColorError)
            } else {
                Res.color(requireContext(), R.attr.themeColorBackgroundSecondary)
            }
        } else {
            throw IllegalArgumentException("Should be LEFT or RIGHT")
        }
    }
}