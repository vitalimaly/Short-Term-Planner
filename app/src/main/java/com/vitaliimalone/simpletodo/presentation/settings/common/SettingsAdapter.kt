package com.vitaliimalone.simpletodo.presentation.settings.common

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.ThemesUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.settings_list_item.*

class SettingsAdapter(
        private val onItemClick: ((Settings) -> Unit)
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {
    enum class Settings(val icon: Drawable, val title: String, var subtitile: String = "") {
        THEME(Res.drawable(R.drawable.ic_theme), Res.string(R.string.settings_theme_title), ThemesUtils.getCurrentThemeName()),
        ARCHIVE(Res.drawable(R.drawable.ic_archive), Res.string(R.string.settings_archive_title), Res.string(R.string.settings_archive_subtitle, 0)),
        RATE(Res.drawable(R.drawable.ic_star), Res.string(R.string.settings_rate_title)),
        INFO(Res.drawable(R.drawable.ic_info), Res.string(R.string.settings_info_title))
    }

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