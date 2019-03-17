package com.vitaliimalone.simpletodo.domain.models

import java.util.*

data class Note(
        var id: String = UUID.randomUUID().toString(),
        var title: String,
        var description: String = "",
        var color: Color = Color.WHITE,
        var createdAt: Long = System.currentTimeMillis(),
        var dueTo: Long,
        var isDone: Boolean = false,
        var isArchived: Boolean = false
) {
    enum class Color {
        WHITE
    }
}
