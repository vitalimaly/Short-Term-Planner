package com.vitaliimalone.simpletodo.presentation.hometab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.GetTasksForHomeTabUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import com.vitaliimalone.simpletodo.presentation.models.HomeTab
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeTabViewModel(
    private val getTasksForHomeTabUseCase: GetTasksForHomeTabUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    private var lastSwipedTask: Task? = null
    val tasksForHomeTab = MutableLiveData<List<Task>>()

    fun getTasksForHomeTab(homeTab: HomeTab) {
        viewModelScope.launch {
            getTasksForHomeTabUseCase.getTasksForTab(homeTab).collect {
                tasksForHomeTab.value = it
            }
        }
    }

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
            updateTaskUseCase.updateTask(task)
        }
    }

    fun undoSwipe() {
        lastSwipedTask?.let {
            updateTask(it)
        }
    }
}