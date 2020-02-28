package com.vitaliimalone.shorttermplanner.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vitaliimalone.shorttermplanner.domain.usecases.GetArchivedTasksCountUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.GetUnarchivedOverdueTasksCountUseCase

class SettingsViewModel(
    getUnarchivedOverdueTasksCountUseCase: GetUnarchivedOverdueTasksCountUseCase,
    getArchivedTasksCountUseCase: GetArchivedTasksCountUseCase
) : ViewModel() {
    val overdueTasksCount = getUnarchivedOverdueTasksCountUseCase.getUnarchivedOverdueTasksCount().asLiveData()
    val archivedTasksCount = getArchivedTasksCountUseCase.getArchivedTasksCount().asLiveData()
}