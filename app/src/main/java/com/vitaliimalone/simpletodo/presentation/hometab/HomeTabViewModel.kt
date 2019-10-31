package com.vitaliimalone.simpletodo.presentation.hometab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.GetTasksForHomeTabUseCase
import com.vitaliimalone.simpletodo.presentation.models.HomeTab
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeTabViewModel(
        private val getTasksForHomeTabUseCase: GetTasksForHomeTabUseCase
) : ViewModel() {
    val tasksForHomeTab = MutableLiveData<List<Task>>()

    fun fetchTasksForHomeTab(homeTab: HomeTab) {
        viewModelScope.launch {
            getTasksForHomeTabUseCase.getTasksForTab(homeTab).collect {
                tasksForHomeTab.value = it
            }
        }
    }
}