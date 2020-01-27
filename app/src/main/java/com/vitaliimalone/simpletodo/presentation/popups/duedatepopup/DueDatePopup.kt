package com.vitaliimalone.simpletodo.presentation.popups.duedatepopup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.home.common.HomeTab
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.DialogUtils
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.views.DefaultDividerItemDecoration
import kotlinx.android.synthetic.main.due_date_popup.view.*
import org.threeten.bp.OffsetDateTime

class DueDatePopup(
    context: Context,
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
                            DialogUtils.showDatePickerDialog(context, currentDate) { date ->
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