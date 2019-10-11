package com.vitaliimalone.simpletodo.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
        var id: String = UUID.randomUUID().toString(),
        var title: String,
        var description: String = "",
        var color: Color = Color.WHITE,
        var createdAt: Long = System.currentTimeMillis(),
        var dueTo: Long,
        var isDone: Boolean = false,
        var isArchived: Boolean = false
) : Parcelable {
    enum class Color {
        WHITE
    }
}
