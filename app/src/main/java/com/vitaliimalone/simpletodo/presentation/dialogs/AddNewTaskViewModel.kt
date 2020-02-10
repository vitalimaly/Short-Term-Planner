package com.vitaliimalone.simpletodo.presentation.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.AddTasksUseCase
import kotlinx.coroutines.launch

class AddNewTaskViewModel(
    private val addTasksUseCase: AddTasksUseCase
) : ViewModel() {
    fun addNewTask(task: Task) {
        viewModelScope.launch {
            addTasksUseCase.addTask(listOf(task))
        }
    }
}