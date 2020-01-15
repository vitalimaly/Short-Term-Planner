package com.vitaliimalone.simpletodo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.AddTaskUseCase
import com.vitaliimalone.simpletodo.presentation.utils.Constants
import com.vitaliimalone.simpletodo.presentation.utils.Pref
import com.vitaliimalone.simpletodo.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class HomeViewModel(
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {
    val showRateDialogEvent = SingleLiveEvent<Unit>()

    fun addNewTask(task: Task) {
        viewModelScope.launch {
            addTaskUseCase.addTask(task)
        }
    }

    fun checkRateDialog() {
        Pref.rateAppLaunchCount += 1
        val isThreeDaysAfterFirstLaunchPassed = OffsetDateTime.now()
            .isAfter(Pref.rateAppLaunchDateTime.plusDays(Constants.DAYS_PASSED_UNTIL_RATE_DIALOG))
        val showRateDialog = (Pref.rateAppDontShow
            || Pref.rateAppLaunchCount >= Constants.LAUNCHES_UNTIL_RATE_DIALOG
            || isThreeDaysAfterFirstLaunchPassed)
        if (showRateDialog) {
            showRateDialogEvent.call()
        }
    }
}
