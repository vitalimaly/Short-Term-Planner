package com.vitaliimalone.shorttermplanner.presentation.screens.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.domain.usecases.AddTasksUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.DeleteArchivedTasksUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.DeleteTaskUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.GetArchivedTasksUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.UpdateTaskUseCase
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class ArchiveViewModel(
    getArchivedTasksUseCase: GetArchivedTasksUseCase,
    private val addTasksUseCase: AddTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteArchivedTasksUseCase: DeleteArchivedTasksUseCase
) : ViewModel() {
    val archivedTasks = getArchivedTasksUseCase.getArchivedTasks().asLiveData()
    private var lastSwipedTask: Task? = null
    private var lastDeletedTasks = listOf<Task>()

    fun deleteTask(task: Task) {
        lastSwipedTask = task.copy()
        viewModelScope.launch {
            deleteTaskUseCase.deleteTask(listOf(task))
        }
    }

    fun undoDelete() {
        lastSwipedTask?.let {
            viewModelScope.launch {
                addTasksUseCase.addTask(listOf(it))
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

    fun deleteAllArchivedTasks() {
        viewModelScope.launch {
            val tasksToDelete = archivedTasks.value
            tasksToDelete?.let {
                lastDeletedTasks = it.map { task -> task.copy() }
            }
            deleteArchivedTasksUseCase.deleteArchivedTasks()
        }
    }

    fun undoDeleteAllArchivedTasks() {
        viewModelScope.launch {
            addTasksUseCase.addTask(lastDeletedTasks)
        }
    }

    fun updateTaskIsDone(task: Task, isDone: Boolean) {
        task.isDone = isDone
        updateTask(task)
    }
}