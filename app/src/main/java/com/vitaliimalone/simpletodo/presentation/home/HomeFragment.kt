package com.vitaliimalone.simpletodo.presentation.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.home.common.TasksAdapter
import com.vitaliimalone.simpletodo.presentation.utils.DateUtils
import com.vitaliimalone.simpletodo.presentation.utils.forceShowKeyboard
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.home_fragment) {
    private val viewModel: HomeViewModel by viewModel()
    private val tasksAdapter by lazy { TasksAdapter(this::onTaskClick, this::onDoneClick) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
        setupClickListeners()
        setupViews()
        viewModel.getAllTasks()
    }

    private fun setupObservers() {
        viewModel.task.observe(viewLifecycleOwner, Observer {
            tasksAdapter.tasks = it
        })
    }

    private fun setupClickListeners() {
        addFab.setOnClickListener {
            forceShowKeyboard()
            titleEditText.postDelayed({ titleEditText?.requestFocus() }, 1000)
        }
        sendButton.setOnClickListener { viewModel.addTask(titleEditText.text.toString()) }
    }

    private fun setupViews() {
        tasksRecyclerView.adapter = tasksAdapter
        tasksRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(),
                RecyclerView.VERTICAL))
    }

    private fun onTaskClick(task: Task) {

    }

    private fun onDoneClick(task: Task) {
        viewModel.onDoneClick(task)
    }
}
