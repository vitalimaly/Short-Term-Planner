package com.vitaliimalone.simpletodo.presentation.overdue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.DeleteTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetOverdueTasksUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OverdueViewModel(
    private val getOverdueTasksUseCase: GetOverdueTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    private var lastSwipedTask: Task? = null
    val overdueTasks = MutableLiveData<List<Task>>()

    init {
        getOverdueTasks()
    }

    fun getOverdueTasks() {
        viewModelScope.launch {
            getOverdueTasksUseCase.getOverdueTasks().collect {
                overdueTasks.value = it
            }
        }
    }

    fun deleteTask(task: Task) {
        lastSwipedTask = task.copy()
        viewModelScope.launch {
            deleteTaskUseCase.deleteTask(task)
        }
    }

    fun undoSwipe() {
        lastSwipedTask?.let {
            viewModelScope.launch {
                updateTaskUseCase.updateTask(it)
            }
        }
    }
}