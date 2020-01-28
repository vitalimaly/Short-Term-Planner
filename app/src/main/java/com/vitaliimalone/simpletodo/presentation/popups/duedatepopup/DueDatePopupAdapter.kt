package com.vitaliimalone.simpletodo.presentation.popups.duedatepopup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.due_date_popup_item.*

class DueDatePopupAdapter(
    private val onItemClicked: ((DueDate) -> Unit)
) : RecyclerView.Adapter<DueDatePopupAdapter.PopupDueDateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopupDueDateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.due_date_popup_item, parent, false)
        return PopupDueDateViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopupDueDateViewHolder, position: Int) {
        holder.bind(DueDate.values()[position])
    }

    override fun getItemCount(): Int {
        return DueDate.values().size
    }

    inner class PopupDueDateViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: DueDate) {
            containerView.setOnClickListener {
                onItemClicked.invoke(item)
            }
            dueDatePopupItemTextView.text = item.getTitle()
        }
    }
}