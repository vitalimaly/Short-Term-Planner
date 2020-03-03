package com.vitaliimalone.shorttermplanner.presentation.screens.archive

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
import kotlinx.android.synthetic.main.archive_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArchiveFragment : BaseFragment(R.layout.archive_fragment) {
    private val viewModel: ArchiveViewModel by viewModel()
    private val tasksAdapter by lazy { TasksAdapter(::onTaskClicked, ::onTaskLongClick) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupClickListeners()
        setupObservers()
    }

    private fun setupViews() {
        toolbar.title = Res.string(R.string.archive_toolbar_title)
        TooltipCompat.setTooltipText(clearAllImageView, Res.string(R.string.clear_all))
        clearAllImageView.setEnabledWithAlpha(false)
        archiveRecyclerView.adapter = tasksAdapter
        archiveRecyclerView.addItemDecoration(
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
                Res.string(R.string.delete),
                Res.string(R.string.delete),
                Res.color(requireContext(), R.attr.themeColorError),
                Res.color(requireContext(), R.attr.themeColorError)
            )
        )
        itemTouchHelper.attachToRecyclerView(archiveRecyclerView)
    }

    private fun onTabSwipe(position: Int) {
        val swipedTask = tasksAdapter.tasks[position]
        viewModel.deleteTask(swipedTask)
        showSnackbar(
            Res.string(R.string.snackbar_task_deleted),
            Res.string(R.string.snackbar_undo),
            { viewModel.undoDelete() }
        )
    }

    private fun setupClickListeners() {
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        clearAllImageView.setOnClickListener {
            viewModel.deleteAllArchivedTasks()
            showSnackbar(
                Res.string(R.string.snackbar_tasks_deleted),
                Res.string(R.string.snackbar_undo),
                { viewModel.undoDeleteAllArchivedTasks() })
        }
    }

    private fun setupObservers() {
        viewModel.archivedTasks.observe(viewLifecycleOwner, Observer {
            clearAllImageView.setEnabledWithAlpha(it.isNotEmpty())
            tasksAdapter.tasks = it
        })
    }

    private fun onTaskClicked(task: Task) {
        val action = ArchiveFragmentDirections.actionArchiveFragmentToTaskDetailsFragment(task)
        findNavController().navigate(action)
    }

    private fun onTaskLongClick(task: Task, coordinates: Point) {
        DueDatePopup(requireContext(), childFragmentManager, task.dueTo) { pickedDate ->
            viewModel.updateTaskDueDate(task, pickedDate)
        }.run {
            showAtLocation(requireView(), Gravity.NO_GRAVITY, coordinates.x, coordinates.y)
        }
    }
}