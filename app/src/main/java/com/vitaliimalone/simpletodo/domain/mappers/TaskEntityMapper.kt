package com.vitaliimalone.simpletodo.domain.mappers

import com.google.gson.Gson
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.presentation.utils.extensions.toIsoDateTimeString

object TaskEntityMapper {
    private val gson by lazy { Gson() }

    fun map(input: Task): TaskEntity {
        return TaskEntity(
            input.id,
            input.title,
            input.description,
            input.createdAt.toIsoDateTimeString(),
            input.modifiedAt.toIsoDateTimeString(),
            input.dueTo.toIsoDateTimeString(),
            input.isDone,
            input.isArchived,
            gson.toJson(input.tags),
            gson.toJson(input.subtasks)
        )
    }
}