package com.vitaliimalone.simpletodo.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.AddTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetTasksUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*

class HomeViewModel(
        private val getTasksUseCase: GetTasksUseCase,
        private val addTaskUseCase: AddTaskUseCase,
        private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    val task by lazy { MutableLiveData<List<Task>>() }

    fun getAllTasks() {
        viewModelScope.launch {
            task.value = getTasksUseCase.getAllTasks()
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            val task = createTask(title)
            addTaskUseCase.addTask(task)
        }
    }

    fun onDoneClick(task: Task) {
        viewModelScope.launch {
            task.isDone = !task.isDone
            updateTaskUseCase.updateTask(task)
        }
    }

    private fun createTask(title: String): Task {
        return Task(title = title)
    }
}
