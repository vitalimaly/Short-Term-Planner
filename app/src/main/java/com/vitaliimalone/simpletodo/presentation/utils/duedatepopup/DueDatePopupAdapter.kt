package com.vitaliimalone.simpletodo.presentation.utils.duedatepopup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.pick_due_date_popup_item.*

class DueDatePopupAdapter(
        private val onItemClicked: ((DueDatePopupItem) -> Unit)
) : RecyclerView.Adapter<DueDatePopupAdapter.PopupDueDateViewHolder>() {
    enum class DueDatePopupItem(val text: String) {
        TODAY(Res.string(R.string.due_date_popup_today)),
        TOMORROW(Res.string(R.string.due_date_popup_tomorrow)),
        END_OF_WEEK(DateTimeUtils.getDueDatePopupEndOfWeekDate()),
        PICK(Res.string(R.string.due_date_popup_pick_date))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopupDueDateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pick_due_date_popup_item, parent, false)
        return PopupDueDateViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopupDueDateViewHolder, position: Int) {
        holder.bind(DueDatePopupItem.values()[position])
    }

    override fun getItemCount(): Int {
        return DueDatePopupItem.values().size
    }

    inner class PopupDueDateViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: DueDatePopupItem) {
            containerView.setOnClickListener {
                onItemClicked.invoke(item)
            }
            dueDatePopupItemTextView.text = item.text
        }
    }
}