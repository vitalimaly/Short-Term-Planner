package com.vitaliimalone.simpletodo.presentation.overdue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.AddTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.DeleteOverdueTasksUseCase
import com.vitaliimalone.simpletodo.domain.usecases.DeleteTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetUnarchivedOverdueTasksUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class OverdueViewModel(
    getUnarchivedOverdueTasksUseCase: GetUnarchivedOverdueTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteOverdueTasksUseCase: DeleteOverdueTasksUseCase
) : ViewModel() {
    val unarchivedOverdueTasks = getUnarchivedOverdueTasksUseCase.getUnarchivedOverdueTasks().asLiveData()
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

    fun updateTaskDueDate(task: Task, dueDate: OffsetDateTime) {
        task.dueTo = dueDate
        viewModelScope.launch {
            updateTaskUseCase.updateTask(task)
        }
    }

    fun deleteAllOverdueTasks() {
        viewModelScope.launch {
            deleteOverdueTasksUseCase.deleteUnarchivedOverdueTasks()
        }
    }
}