package com.vitaliimalone.shorttermplanner.presentation.popups.duedatepopup

import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.utils.DateTimeUtils
import com.vitaliimalone.shorttermplanner.presentation.utils.Res

enum class DueDate {
    TODAY {
        override fun getTitle() = Res.string(R.string.due_date_popup_today)
    },
    TOMORROW {
        override fun getTitle() = Res.string(R.string.due_date_popup_tomorrow)
    },
    END_OF_WEEK {
        override fun getTitle() = DateTimeUtils.getDueDatePopupEndOfWeekDateText()
    },
    PICK {
        override fun getTitle() = Res.string(R.string.due_date_popup_pick_date)
    };

    abstract fun getTitle(): String
}
