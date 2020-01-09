package com.vitaliimalone.simpletodo.presentation.settings.common

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Res
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
        holder.bind(Theme.values()[position])
    }

    override fun getItemCount(): Int {
        return Theme.values().size
    }

    inner class ThemeViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Theme) {
            containerView.setOnClickListener {
                onItemClicked.invoke(item)
            }
            themeNameTextView.text = item.title
            themeColorPrimaryVariantView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeColorPrimaryVariant, item.styleResId)
            )
            themeColorPrimaryView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeColorPrimary, item.styleResId)
            )
            themeColorSecondaryVariantView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeColorSecondaryVariant, item.styleResId)
            )
            themeColorSecondaryView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeColorSecondary, item.styleResId)
            )
            themeTextColorPrimaryView.setBackgroundColor(
                Res.themeColor(itemView.context, R.attr.themeTextColorPrimary, item.styleResId)
            )
            themeStartRadioButton.supportButtonTintList = ColorStateList.valueOf(
                Res.themeColor(itemView.context, R.attr.themeTextColorSecondary, item.styleResId)
            )
            themeEndRadioButton.supportButtonTintList = ColorStateList.valueOf(
                Res.themeColor(itemView.context, R.attr.themeTextColorSecondary, item.styleResId)
            )
        }
    }
}