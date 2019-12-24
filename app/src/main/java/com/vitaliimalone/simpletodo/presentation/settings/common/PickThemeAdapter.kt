package com.vitaliimalone.simpletodo.presentation.settings.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Theme
import com.vitaliimalone.simpletodo.presentation.utils.ThemesUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.pick_due_date_popup_item.*

class PickThemeAdapter(
    private val onItemClicked: ((Theme) -> Unit)
) : RecyclerView.Adapter<PickThemeAdapter.PickThemeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickThemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pick_due_date_popup_item, parent, false)
        return PickThemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PickThemeViewHolder, position: Int) {
        holder.bind(ThemesUtils.themes[position])
    }

    override fun getItemCount(): Int {
        return ThemesUtils.themes.size
    }

    inner class PickThemeViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Theme) {
            containerView.setOnClickListener {
                onItemClicked.invoke(item)
            }
            dueDatePopupItemTextView.text = item.name
        }
    }
}