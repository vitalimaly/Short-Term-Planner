package com.vitaliimalone.simpletodo.presentation.settings.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.models.Language
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.language_dialog_item.*

class LanguageAdapter(
    private val onItemClicked: ((Language) -> Unit)
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.language_dialog_item, parent, false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(Language.values()[position])
    }

    override fun getItemCount(): Int {
        return Language.values().size
    }

    inner class LanguageViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Language) {
            containerView.setOnClickListener {
                onItemClicked.invoke(item)
            }
            languageDialogItemTextView.text = item.title
        }
    }
}