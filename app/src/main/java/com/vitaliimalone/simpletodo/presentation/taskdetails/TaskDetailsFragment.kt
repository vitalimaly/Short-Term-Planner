package com.vitaliimalone.simpletodo.presentation.taskdetails

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.task_details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskDetailsFragment : BaseFragment(R.layout.task_details_fragment) {
    private val viewModel: TaskDetailsViewModel by viewModel()
    private val args: TaskDetailsFragmentArgs by navArgs()
    private val task: Task by lazy { args.task }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupViews()
        setupObservers()
    }

    private fun setupClickListeners() {
        deleteIv.setOnClickListener {
            viewModel.deleteTask(task)
            findNavController().popBackStack()
        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupViews() {

    }

    private fun setupObservers() {

    }
}
