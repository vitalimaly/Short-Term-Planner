package com.vitaliimalone.simpletodo.presentation.settings.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.settings_list_item.*

class SettingsAdapter(
    private val onItemClick: ((Settings) -> Unit)
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.settings_list_item, parent, false)
        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(Settings.values()[position])
    }

    override fun getItemCount(): Int {
        return Settings.values().size
    }

    fun updateSetting(settings: Settings) {
        notifyItemChanged(settings.ordinal)
    }

    inner class SettingsViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Settings) {
            containerView.setOnClickListener {
                onItemClick.invoke(item)
            }
            settingIconImageView.setImageDrawable(item.icon)
            settingTitleTextView.text = item.title
            settingSubtitleTextView.text = item.subtitile
            settingSubtitleTextView.isVisible = item.subtitile.isNotEmpty()
        }
    }
}