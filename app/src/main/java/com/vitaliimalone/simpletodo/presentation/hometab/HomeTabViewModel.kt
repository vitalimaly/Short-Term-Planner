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
    val tasksForHomeTab = MutableLiveData<List<Task>>()

    fun fetchTasksForHomeTab(homeTab: HomeTab) {
        viewModelScope.launch {
            getTasksForHomeTabUseCase.getTasksForTab(homeTab).collect {
                tasksForHomeTab.value = it
            }
        }
    }

    fun onSwipeLeft(homeTab: HomeTab, task: Task) {
        when (homeTab) {
            HomeTab.TODAY -> {
                task.isArchived = true
            }
            HomeTab.WEEK -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTab.TODAY)
            }
            HomeTab.MONTH -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTab.WEEK)
            }
            HomeTab.TODO -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTab.MONTH)
            }
        }
        viewModelScope.launch {
            updateTaskUseCase.updateTask(task)
        }
    }

    fun onSwipeRight(homeTab: HomeTab, task: Task) {
        when (homeTab) {
            HomeTab.TODAY -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTab.WEEK)
            }
            HomeTab.WEEK -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTab.MONTH)
            }
            HomeTab.MONTH -> {
                task.dueTo = DateTimeUtils.getDateForAddNewTask(HomeTab.TODO)
            }
            HomeTab.TODO -> {
                task.isArchived = true
            }
        }
        viewModelScope.launch {
            updateTaskUseCase.updateTask(task)
        }
    }
}