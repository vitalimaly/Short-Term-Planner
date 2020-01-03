package com.vitaliimalone.simpletodo.presentation.utils.duedatepopup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.DateTimeUtils
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.due_date_popup_item.*

class DueDatePopupAdapter(
    private val onItemClicked: ((DueDateItem) -> Unit)
) : RecyclerView.Adapter<DueDatePopupAdapter.PopupDueDateViewHolder>() {
    private val items = listOf(
        DueDateItem(DueDateType.TODAY, Res.string(R.string.due_date_popup_today)),
        DueDateItem(DueDateType.TOMORROW, Res.string(R.string.due_date_popup_tomorrow)),
        DueDateItem(DueDateType.END_OF_WEEK, DateTimeUtils.getDueDatePopupEndOfWeekDate()),
        DueDateItem(DueDateType.PICK, Res.string(R.string.due_date_popup_pick_date))
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopupDueDateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.due_date_popup_item, parent, false)
        return PopupDueDateViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopupDueDateViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PopupDueDateViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: DueDateItem) {
            containerView.setOnClickListener {
                onItemClicked.invoke(item)
            }
            dueDatePopupItemTextView.text = item.text
        }
    }
}