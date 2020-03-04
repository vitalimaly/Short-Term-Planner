package com.vitaliimalone.shorttermplanner.presentation.screens.overdue

import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.widget.TooltipCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.presentation.base.BaseFragment
import com.vitaliimalone.shorttermplanner.presentation.popups.duedatepopup.DueDatePopup
import com.vitaliimalone.shorttermplanner.presentation.screens.hometab.common.TaskTouchHelperCallback
import com.vitaliimalone.shorttermplanner.presentation.screens.hometab.common.TasksAdapter
import com.vitaliimalone.shorttermplanner.presentation.utils.Res
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.setEnabledWithAlpha
import com.vitaliimalone.shorttermplanner.presentation.views.DefaultDividerItemDecoration
import kotlinx.android.synthetic.main.overdue_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverdueFragment : BaseFragment(R.layout.overdue_fragment) {
    private val viewModel: OverdueViewModel by viewModel()
    private val tasksAdapter by lazy { TasksAdapter(::onTaskClicked, ::onTaskLongClick, ::onTaskDoneClick) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupClickListeners()
        setupObservers()
    }

    private fun setupViews() {
        toolbar.title = Res.string(R.string.overdue_toolbar_title)
        TooltipCompat.setTooltipText(archiveAllImageView, Res.string(R.string.archive_all))
        archiveAllImageView.setEnabledWithAlpha(false)
        overdueRecyclerView.adapter = tasksAdapter
        overdueRecyclerView.addItemDecoration(
            DefaultDividerItemDecoration(
                requireContext(),
                marginLeft = Res.dimen(requireContext(), R.dimen.home_divider_margin),
                marginRight = Res.dimen(requireContext(), R.dimen.home_divider_margin)
            )
        )
        val itemTouchHelper = ItemTouchHelper(
            TaskTouchHelperCallback(
                requireContext(),
                { position, _ -> onTabSwipe(position) },
                Res.string(R.string.archive),
                Res.string(R.string.archive),
                Res.color(requireContext(), R.attr.themeColorError),
                Res.color(requireContext(), R.attr.themeColorError)
            )
        )
        itemTouchHelper.attachToRecyclerView(overdueRecyclerView)
    }

    private fun onTabSwipe(position: Int) {
        val swipedTask = tasksAdapter.tasks[position]
        viewModel.archiveTask(swipedTask)
        showSnackbar(
            Res.string(R.string.snackbar_task_archived),
            Res.string(R.string.snackbar_undo),
            { viewModel.undoArchive() })
    }

    private fun setupClickListeners() {
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        archiveAllImageView.setOnClickListener {
            viewModel.archiveAllOverdueTasks()
            showSnackbar(
                Res.string(R.string.snackbar_tasks_archived),
                Res.string(R.string.snackbar_undo),
                { viewModel.undoArchiveAllOverdueTasks() })
        }
    }

    private fun setupObservers() {
        viewModel.unarchivedOverdueTasks.observe(viewLifecycleOwner, Observer {
            archiveAllImageView.setEnabledWithAlpha(it.isNotEmpty())
            tasksAdapter.tasks = it
        })
    }

    private fun onTaskClicked(task: Task) {
        val action = OverdueFragmentDirections.actionOverdueFragmentToTaskDetailsFragment(task)
        findNavController().navigate(action)
    }

    private fun onTaskLongClick(task: Task, coordinates: Point) {
        DueDatePopup(requireContext(), childFragmentManager, task.dueTo) { pickedDate ->
            viewModel.updateTaskDueDate(task, pickedDate)
        }.run {
            showAtLocation(requireView(), Gravity.NO_GRAVITY, coordinates.x, coordinates.y)
        }
    }

    private fun onTaskDoneClick(task: Task, isDone: Boolean) {
        viewModel.updateTaskIsDone(task, isDone)
    }
}