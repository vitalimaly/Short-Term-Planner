package com.vitaliimalone.simpletodo.presentation.hometab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.GetTasksForHomeTabUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.HomeTabType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeTabViewModel(
    private val getTasksForHomeTabUseCase: GetTasksForHomeTabUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    private var lastSwipedTask: Task? = null
    val tasksForHomeTab = MutableLiveData<List<Task>>()

    fun fetchTasksForHomeTab(homeTab: HomeTabType) {
        viewModelScope.launch {
            getTasksForHomeTabUseCase.getTasksForTab(homeTab).collect {
                tasksForHomeTab.value = it
            }
        }
    }

    fun onSwipeLeft(homeTab: HomeTabType, task: Task) {
        lastSwipedTask = task.copy()
        when (homeTab) {
            HomeTabType.TODAY -> {
                task.isArchived = true
            }
            HomeTabType.WEEK -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTabType.TODAY)
            }
            HomeTabType.MONTH -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTabType.WEEK)
            }
            HomeTabType.TODO -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTabType.MONTH)
            }
        }
        updateTask(task)
    }

    fun onSwipeRight(homeTab: HomeTabType, task: Task) {
        lastSwipedTask = task.copy()
        when (homeTab) {
            HomeTabType.TODAY -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTabType.WEEK)
            }
            HomeTabType.WEEK -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTabType.MONTH)
            }
            HomeTabType.MONTH -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTabType.TODO)
            }
            HomeTabType.TODO -> {
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