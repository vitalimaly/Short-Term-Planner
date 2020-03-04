package com.vitaliimalone.shorttermplanner.presentation.screens.overdue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.domain.usecases.GetUnarchivedOverdueTasksUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.UpdateTaskUseCase
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class OverdueViewModel(
    getUnarchivedOverdueTasksUseCase: GetUnarchivedOverdueTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    val unarchivedOverdueTasks = getUnarchivedOverdueTasksUseCase.getUnarchivedOverdueTasks().asLiveData()
    private var lastSwipedTask: Task? = null
    private var lastArchivedTasks = listOf<Task>()

    fun archiveTask(task: Task) {
        lastSwipedTask = task.copy()
        task.isArchived = true
        updateTask(task)
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
        updateTask(task)
    }

    private fun updateTask(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase.updateTasks(listOf(task))
        }
    }

    fun archiveAllOverdueTasks() {
        viewModelScope.launch {
            val tasksToArchive = unarchivedOverdueTasks.value
            tasksToArchive?.let {
                lastArchivedTasks = it.map { task -> task.copy() }
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

    fun updateTaskIsDone(task: Task, isDone: Boolean) {
        task.isDone = isDone
        updateTask(task)
    }
}