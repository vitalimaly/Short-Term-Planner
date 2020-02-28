package com.vitaliimalone.shorttermplanner.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.vitaliimalone.shorttermplanner.presentation.utils.Constants
import com.vitaliimalone.shorttermplanner.presentation.utils.Pref
import com.vitaliimalone.shorttermplanner.presentation.utils.SingleLiveEvent
import org.threeten.bp.OffsetDateTime

class HomeViewModel : ViewModel() {
    val showRateDialogEvent = SingleLiveEvent<Unit>()

    fun checkRateDialog() {
        Pref.rateAppLaunchCount += 1
        val isThreeDaysAfterFirstLaunchPassed = OffsetDateTime.now()
            .isAfter(Pref.rateAppLaunchDateTime.plusDays(Constants.DAYS_PASSED_UNTIL_RATE_DIALOG))
        val showRateDialog = !Pref.rateAppNeverShowClicked
            && (Pref.rateAppLaunchCount >= Constants.LAUNCHES_UNTIL_RATE_DIALOG || isThreeDaysAfterFirstLaunchPassed)
        if (showRateDialog) {
            showRateDialogEvent.call()
        }
    }
}
