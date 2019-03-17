package com.vitaliimalone.simpletodo.presentation.base

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BindingViewHolder<in ITEM, in VIEW_MODEL>(override val containerView: View)
    : RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun bind(item: ITEM, viewModel: VIEW_MODEL)

    protected fun getString(@StringRes resId: Int): String? = containerView.context.getString(resId)

    protected fun <T : View> findView(@IdRes resId: Int): T? = containerView.findViewById<T?>(resId)

    protected val context: Context get() = containerView.context

}
