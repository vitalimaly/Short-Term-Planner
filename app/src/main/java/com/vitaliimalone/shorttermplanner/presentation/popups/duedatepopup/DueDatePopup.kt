package com.vitaliimalone.shorttermplanner.presentation.popups.duedatepopup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.fragment.app.FragmentManager
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.dialogs.DatePickerDialog
import com.vitaliimalone.shorttermplanner.presentation.screens.home.common.HomeTab
import com.vitaliimalone.shorttermplanner.presentation.utils.DateTimeUtils
import com.vitaliimalone.shorttermplanner.presentation.utils.Res
import com.vitaliimalone.shorttermplanner.presentation.views.DefaultDividerItemDecoration
import kotlinx.android.synthetic.main.due_date_popup.view.*
import org.threeten.bp.OffsetDateTime

class DueDatePopup(
    context: Context,
    private val fragmentManager: FragmentManager,
    private val currentDate: OffsetDateTime,
    private val onDatePicked: ((OffsetDateTime) -> Unit)
) : PopupWindow(
    LayoutInflater.from(context).inflate(R.layout.due_date_popup, null, false),
    WindowManager.LayoutParams.WRAP_CONTENT,
    WindowManager.LayoutParams.WRAP_CONTENT,
    true
) {
    init {
        isOutsideTouchable = false
        elevation = Res.dimen(context, R.dimen.default_popup_window_elevation)
    }

    override fun setContentView(contentView: View?) {
        super.setContentView(contentView)
        contentView?.let {
            it.apply {
                rootView.setOnClickListener { dismiss() }
                dueDatePopupRecyclerView.addItemDecoration(
                    DefaultDividerItemDecoration(
                        context,
                        marginLeft = Res.dimen(context, R.dimen.m_spacing),
                        marginRight = Res.dimen(context, R.dimen.m_spacing)
                    )
                )
                dueDatePopupRecyclerView.adapter = DueDatePopupAdapter { dueDate ->
                    when (dueDate) {
                        DueDate.TODAY -> {
                            onDatePicked.invoke(OffsetDateTime.now())
                        }
                        DueDate.TOMORROW -> {
                            onDatePicked.invoke(OffsetDateTime.now().plusDays(1))
                        }
                        DueDate.END_OF_WEEK -> {
                            onDatePicked.invoke(DateTimeUtils.getTabStartEndDate(HomeTab.WEEK).second)
                        }
                        DueDate.PICK -> {
                            DatePickerDialog.show(fragmentManager, currentDate) { date ->
                                onDatePicked.invoke(date)
                            }
                        }
                    }
                    dismiss()
                }
            }
        }
    }
}