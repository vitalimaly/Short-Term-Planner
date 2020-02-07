package com.vitaliimalone.simpletodo.presentation.overdue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.GetUnarchivedOverdueTasksUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class OverdueViewModel(
    getUnarchivedOverdueTasksUseCase: GetUnarchivedOverdueTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    val unarchivedOverdueTasks = getUnarchivedOverdueTasksUseCase.getUnarchivedOverdueTasks().asLiveData()
    private var lastSwipedTask: Task? = null
    private val lastArchivedTasks = mutableListOf<Task>()

    fun archiveTask(task: Task) {
        lastSwipedTask = task.copy()
        task.isArchived = true
        viewModelScope.launch {
            updateTaskUseCase.updateTasks(listOf(task))
        }
    }

    fun undoArchive() {
        lastSwipedTask?.let {
            viewModelScope.launch {
                updateTaskUseCase.updateTasks(listOf(it))
            }
        }
    }

    fun updateTaskDueDate(task: Task, dueDate: OffsetDateTime) {
        task.dueTo = dueDate
        viewModelScope.launch {
            updateTaskUseCase.updateTasks(listOf(task))
        }
    }

    fun archiveAllOverdueTasks() {
        viewModelScope.launch {
            lastArchivedTasks.clear()
            val tasksToArchive = unarchivedOverdueTasks.value
            tasksToArchive?.let {
                lastArchivedTasks.addAll(it)
                tasksToArchive.map { task -> task.isArchived = true }
                updateTaskUseCase.updateTasks(tasksToArchive)
            }
        }
    }

    fun undoArchiveAllOverdueTasks() {
        viewModelScope.launch {
            updateTaskUseCase.updateTasks(lastArchivedTasks)
        }
    }
}