package com.vitaliimalone.shorttermplanner.presentation.dialogs.addnewtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.domain.usecases.AddTasksUseCase
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