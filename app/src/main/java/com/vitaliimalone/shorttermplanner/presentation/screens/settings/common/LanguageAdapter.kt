package com.vitaliimalone.shorttermplanner.presentation.screens.settings.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.utils.LanguageUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.language_dialog_item.*

class LanguageAdapter(
    private val onItemClicked: ((Language) -> Unit)
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.language_dialog_item, parent, false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) = holder.bind(Language.values()[position])

    override fun getItemCount() = Language.values().size

    inner class LanguageViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Language) {
            containerView.setOnClickListener {
                onItemClicked.invoke(item)
            }
            languageDialogItemTextView.text = item.getTitle()
            iconImageView.setImageDrawable(item.getIcon(containerView.context))
            checkmarkImageView.isVisible = item == LanguageUtils.getCurrentLanguage()
        }
    }
}