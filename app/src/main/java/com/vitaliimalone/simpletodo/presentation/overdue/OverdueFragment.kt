package com.vitaliimalone.simpletodo.presentation.overdue

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.hometab.common.TaskTouchHelperCallback
import com.vitaliimalone.simpletodo.presentation.hometab.common.TasksAdapter
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.views.DefaultDividerItemDecoration
import kotlinx.android.synthetic.main.overdue_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverdueFragment : BaseFragment(R.layout.overdue_fragment) {
    private val viewModel: OverdueViewModel by viewModel()
    private val tasksAdapter by lazy { TasksAdapter(::onTaskClicked) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupClickListeners()
        setupObservers()
    }

    private fun setupViews() {
        toolbar.title = Res.string(R.string.overdue_toolbar_title)
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
                Res.string(R.string.delete),
                Res.string(R.string.delete),
                Res.color(requireContext(), R.attr.themeColorError),
                Res.color(requireContext(), R.attr.themeColorError)
            )
        )
        itemTouchHelper.attachToRecyclerView(overdueRecyclerView)
    }

    private fun onTabSwipe(position: Int) {
        val swipedTask = tasksAdapter.tasks[position]
        viewModel.deleteTask(swipedTask)
    }

    private fun setupClickListeners() {
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    private fun setupObservers() {
        viewModel.overdueTasks.observe(viewLifecycleOwner, Observer {
            tasksAdapter.tasks = it
        })
    }

    private fun onTaskClicked(task: Task) {
        val action = OverdueFragmentDirections.actionOverdueFragmentToTaskDetailsFragment(task)
        findNavController().navigate(action)
    }
}