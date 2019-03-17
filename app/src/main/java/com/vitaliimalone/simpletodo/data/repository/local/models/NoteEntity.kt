package com.vitaliimalone.simpletodo.data.repository.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
        @PrimaryKey
        var id: String,
        var title: String,
        var description: String,
        var color: Int,
        var createdAt: Long,
        var dueTo: Long,
        var isDone: Boolean,
        var isArchived: Boolean
)
