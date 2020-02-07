package com.vitaliimalone.simpletodo.presentation.overdue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.ArchiveOverdueTasksUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetUnarchivedOverdueTasksUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class OverdueViewModel(
    getUnarchivedOverdueTasksUseCase: GetUnarchivedOverdueTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val archiveOverdueTasksUseCase: ArchiveOverdueTasksUseCase
) : ViewModel() {
    val unarchivedOverdueTasks = getUnarchivedOverdueTasksUseCase.getUnarchivedOverdueTasks().asLiveData()
    private var lastSwipedTask: Task? = null

    fun archiveTask(task: Task) {
        lastSwipedTask = task.copy()
        task.isArchived = true
        viewModelScope.launch {
            updateTaskUseCase.updateTask(task)
        }
    }

    fun undoArchive() {
        lastSwipedTask?.let {
            viewModelScope.launch {
                updateTaskUseCase.updateTask(it)
            }
        }
    }

    fun updateTaskDueDate(task: Task, dueDate: OffsetDateTime) {
        task.dueTo = dueDate
        viewModelScope.launch {
            updateTaskUseCase.updateTask(task)
        }
    }

    fun archiveAllOverdueTasks() {
        viewModelScope.launch {
            archiveOverdueTasksUseCase.archiveUnarchivedOverdueTasks()
        }
    }
}