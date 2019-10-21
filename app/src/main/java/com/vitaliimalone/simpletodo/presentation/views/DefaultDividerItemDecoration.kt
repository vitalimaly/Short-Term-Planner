package com.vitaliimalone.simpletodo.presentation.views

import android.content.Context
import android.graphics.drawable.InsetDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.vitaliimalone.simpletodo.R

class DefaultDividerItemDecoration(
        context: Context,
        drawableResourceId: Int? = null,
        marginLeft: Float = 0f,
        marginTop: Float = 0f,
        marginRight: Float = 0f,
        marginBottom: Float = 0f
) : DividerItemDecoration(context, VERTICAL) {

    init {
        val drawable = ContextCompat.getDrawable(context, drawableResourceId ?: R.drawable.divider_default)!!
        val insetDivider = InsetDrawable(drawable, marginLeft.toInt(), marginTop.toInt(), marginRight.toInt(),
                marginBottom.toInt())
        setDrawable(insetDivider)
    }
}