package com.vitaliimalone.simpletodo.presentation.settings.common

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.Theme
import com.vitaliimalone.simpletodo.presentation.utils.ThemesUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.pick_theme_dialog_item.*

class PickThemeAdapter(
    private val onItemClicked: ((Theme) -> Unit)
) : RecyclerView.Adapter<PickThemeAdapter.PickThemeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickThemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pick_theme_dialog_item, parent, false)
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
            themeNameTextView.text = item.name
            themeColorPrimaryVariantView.setBackgroundColor(
                Res.color(itemView.context, R.attr.themeColorPrimaryVariant, item.resId)
            )
            themeColorPrimaryView.setBackgroundColor(
                Res.color(itemView.context, R.attr.themeColorPrimary, item.resId)
            )
            themeColorSecondaryVariantView.setBackgroundColor(
                Res.color(itemView.context, R.attr.themeColorSecondaryVariant, item.resId)
            )
            themeColorSecondaryView.setBackgroundColor(
                Res.color(itemView.context, R.attr.themeColorSecondary, item.resId)
            )
            themeTextColorPrimaryView.setBackgroundColor(
                Res.color(itemView.context, R.attr.themeTextColorPrimary, item.resId)
            )
            themeStartRadioButton.supportButtonTintList = ColorStateList.valueOf(
                Res.color(itemView.context, R.attr.themeTextColorSecondary, item.resId)
            )
            themeEndRadioButton.supportButtonTintList = ColorStateList.valueOf(
                Res.color(itemView.context, R.attr.themeTextColorSecondary, item.resId)
            )
        }
    }
}