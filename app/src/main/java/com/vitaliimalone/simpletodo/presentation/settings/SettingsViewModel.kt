package com.vitaliimalone.simpletodo.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vitaliimalone.simpletodo.domain.usecases.GetOverdueTasksCountUseCase

class SettingsViewModel(
    getOverdueTasksCountUseCase: GetOverdueTasksCountUseCase
) : ViewModel() {
    val overdueTasksCount = getOverdueTasksCountUseCase.getOverdueTasksCount().asLiveData()
}