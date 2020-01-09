package com.vitaliimalone.simpletodo.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vitaliimalone.simpletodo.domain.usecases.GetArchivedTasksCountUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetUnarchivedOverdueTasksCountUseCase

class SettingsViewModel(
    getUnarchivedOverdueTasksCountUseCase: GetUnarchivedOverdueTasksCountUseCase,
    getArchivedTasksCountUseCase: GetArchivedTasksCountUseCase
) : ViewModel() {
    val overdueTasksCount = getUnarchivedOverdueTasksCountUseCase.getUnarchivedOverdueTasksCount().asLiveData()
    val archivedTasksCount = getArchivedTasksCountUseCase.getArchivedTasksCount().asLiveData()
}