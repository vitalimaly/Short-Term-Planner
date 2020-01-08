package com.vitaliimalone.simpletodo.presentation.overdue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.AddTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.DeleteTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetOverdueTasksUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OverdueViewModel(
    private val getOverdueTasksUseCase: GetOverdueTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    val overdueTasks = MutableLiveData<List<Task>>()
    private var lastSwipedTask: Task? = null

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

    fun undoDelete() {
        lastSwipedTask?.let {
            viewModelScope.launch {
                addTaskUseCase.addTask(it)
            }
        }
    }
}