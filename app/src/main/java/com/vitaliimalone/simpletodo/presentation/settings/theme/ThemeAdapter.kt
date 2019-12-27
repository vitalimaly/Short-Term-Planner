package com.vitaliimalone.simpletodo.presentation.settings.theme

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res
import com.vitaliimalone.simpletodo.presentation.utils.Theme
import com.vitaliimalone.simpletodo.presentation.utils.Themes
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.theme_dialog_item.*

class ThemeAdapter(
    private val onItemClicked: ((Theme) -> Unit)
) : RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.theme_dialog_item, parent, false)
        return ThemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bind(Themes.themes[position])
    }

    override fun getItemCount(): Int {
        return Themes.themes.size
    }

    inner class ThemeViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Theme) {
            containerView.setOnClickListener {
                onItemClicked.invoke(item)
            }
            themeNameTextView.text = item.name
            themeColorPrimaryVariantView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeColorPrimaryVariant, item.resId)
            )
            themeColorPrimaryView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeColorPrimary, item.resId)
            )
            themeColorSecondaryVariantView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeColorSecondaryVariant, item.resId)
            )
            themeColorSecondaryView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeColorSecondary, item.resId)
            )
            themeTextColorPrimaryView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeTextColorPrimary, item.resId)
            )
            themeStartRadioButton.supportButtonTintList = ColorStateList.valueOf(
                Res.themeColor(itemView.context, R.attr.themeTextColorSecondary, item.resId)
            )
            themeEndRadioButton.supportButtonTintList = ColorStateList.valueOf(
                Res.themeColor(itemView.context, R.attr.themeTextColorSecondary, item.resId)
            )
        }
    }
}