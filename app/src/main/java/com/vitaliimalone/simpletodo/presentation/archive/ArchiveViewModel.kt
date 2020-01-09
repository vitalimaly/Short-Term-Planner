package com.vitaliimalone.simpletodo.presentation.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.AddTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.DeleteTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetArchivedTasksUseCase
import kotlinx.coroutines.launch

class ArchiveViewModel(
    getArchivedTasksUseCase: GetArchivedTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    val archivedTasks = getArchivedTasksUseCase.getArchivedTasks().asLiveData()
    private var lastSwipedTask: Task? = null

    fun deleteTask(task: Task) {
        lastSwipedTask = task.copy()
        viewModelScope.launch {
            deleteTaskUseCase.deleteTask(task)
        }
    }

    fun undoDelete() {
        lastSwipedTask?.let {
            viewModelScope.launch {
                addTaskUseCase.addTask(it)
            }
        }
    }
}