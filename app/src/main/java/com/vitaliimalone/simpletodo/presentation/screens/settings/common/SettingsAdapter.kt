package com.vitaliimalone.simpletodo.presentation.screens.settings.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.settings_list_item.*

class SettingsAdapter(
    private val onItemClick: ((Setting) -> Unit)
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {
    val settings = listOf(
        Setting.Theme,
        Setting.Language,
        Setting.Overdue,
        Setting.Archive,
        Setting.Rate,
        Setting.About
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.settings_list_item, parent, false)
        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) = holder.bind(settings[position])

    override fun getItemCount() = settings.size

    inner class SettingsViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Setting) {
            containerView.setOnClickListener {
                onItemClick.invoke(item)
            }
            settingIconImageView.setImageDrawable(item.getIcon(itemView.context))
            settingTitleTextView.text = item.getTitle()
            settingSubtitleTextView.text = item.getSubtitle()
            settingSubtitleTextView.isVisible = item.getSubtitle().isNotEmpty()
        }
    }
}