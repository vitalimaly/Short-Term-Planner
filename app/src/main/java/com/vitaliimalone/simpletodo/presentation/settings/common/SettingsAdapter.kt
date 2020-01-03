package com.vitaliimalone.simpletodo.presentation.settings.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Languages
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.Themes
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.settings_list_item.*

class SettingsAdapter(
    context: Context,
    private val onItemClick: ((Settings) -> Unit)
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {
    val settings = listOf(
        Settings(
            SettingsType.THEME,
            Res.drawable(context, R.drawable.ic_theme),
            Res.string(R.string.settings_theme_title),
            Themes.getCurrentTheme(context).name
        ),
        Settings(
            SettingsType.LANGUAGE,
            Res.drawable(context, R.drawable.ic_language),
            Res.string(R.string.settings_language_title),
            Languages.getCurrentLanguage(context).name
        ),
        Settings(
            SettingsType.OVERDUE,
            Res.drawable(context, R.drawable.ic_overdue),
            Res.string(R.string.settings_overdue_title),
            Res.string(R.string.settings_overdue_subtitle, 0)
        ),
        Settings(
            SettingsType.ARCHIVE,
            Res.drawable(context, R.drawable.ic_archive),
            Res.string(R.string.settings_archive_title),
            Res.string(R.string.settings_archive_subtitle, 0)
        ),
        Settings(
            SettingsType.RATE,
            Res.drawable(context, R.drawable.ic_star),
            Res.string(R.string.settings_rate_title)
        ),
        Settings(
            SettingsType.INFO,
            Res.drawable(context, R.drawable.ic_info),
            Res.string(R.string.settings_info_title)
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.settings_list_item, parent, false)
        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(settings[position])
    }

    override fun getItemCount(): Int {
        return settings.size
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