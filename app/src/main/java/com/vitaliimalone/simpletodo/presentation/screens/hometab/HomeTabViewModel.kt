package com.vitaliimalone.simpletodo.presentation.screens.hometab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.GetTasksForHomeTabUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import com.vitaliimalone.simpletodo.presentation.screens.home.common.HomeTab
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class HomeTabViewModel(
    private val getTasksForHomeTabUseCase: GetTasksForHomeTabUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    private var lastSwipedTask: Task? = null

    fun getTasksForHomeTab(homeTab: HomeTab) = getTasksForHomeTabUseCase.getTasksForTab(homeTab).asLiveData()

    fun onSwipeLeft(homeTab: HomeTab, task: Task) {
        lastSwipedTask = task.copy()
        when (homeTab) {
            HomeTab.TODAY -> {
                task.isArchived = true
            }
            HomeTab.WEEK -> {
                task.dueTo = DateTimeUtils.getAddNewTaskDate(HomeTab.TODAY)
            }
            HomeTab.MONTH -> {
                task.dueTo = DateTimeUtils.getAddNewTaskDate(HomeTab.WEEK)
            }
            HomeTab.TODO -> {
                task.dueTo = DateTimeUtils.getAddNewTaskDate(HomeTab.MONTH)
            }
        }
        updateTask(task)
    }

    fun onSwipeRight(homeTab: HomeTab, task: Task) {
        lastSwipedTask = task.copy()
        when (homeTab) {
            HomeTab.TODAY -> {
                task.dueTo = DateTimeUtils.getAddNewTaskDate(HomeTab.WEEK)
            }
            HomeTab.WEEK -> {
                task.dueTo = DateTimeUtils.getAddNewTaskDate(HomeTab.MONTH)
            }
            HomeTab.MONTH -> {
                task.dueTo = DateTimeUtils.getAddNewTaskDate(HomeTab.TODO)
            }
            HomeTab.TODO -> {
                task.isArchived = true
            }
        }
        updateTask(task)
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase.updateTasks(listOf(task))
        }
    }

    fun undoSwipe() {
        lastSwipedTask?.let {
            updateTask(it)
        }
    }

    fun updateTaskDueDate(task: Task, dueDate: OffsetDateTime) {
        task.dueTo = dueDate
        updateTask(task)
    }
}