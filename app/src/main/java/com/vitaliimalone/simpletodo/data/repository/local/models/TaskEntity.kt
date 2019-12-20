package com.vitaliimalone.simpletodo.data.repository.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey
    var id: String,
    var title: String,
    var description: String,
    var createdAt: String,
    var modifiedAt: String,
    var dueTo: String,
    var isDone: Boolean,
    var isArchived: Boolean,
    var tags: String,
    var subtasks: String
)
