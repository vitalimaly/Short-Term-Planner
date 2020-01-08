package com.vitaliimalone.simpletodo.presentation.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.usecases.GetOverdueTasksCountUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getOverdueTasksCountUseCase: GetOverdueTasksCountUseCase
) : ViewModel() {
    val overdueTasksCount = MutableLiveData<Int>()

    fun getOverdueTasksCount() {
        viewModelScope.launch {
            getOverdueTasksCountUseCase.getOverdueTasksCount().collect {
                overdueTasksCount.value = it
            }
        }
    }
}