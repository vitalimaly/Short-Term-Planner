package com.vitaliimalone.simpletodo.presentation.taskspageritem

import android.os.Bundle
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TasksPagerItemFragment : BaseFragment(R.layout.tasks_pager_item_fragment) {
    private val itemViewModel: TasksPagerItemViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
