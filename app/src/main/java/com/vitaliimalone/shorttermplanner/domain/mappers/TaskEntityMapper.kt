package com.vitaliimalone.shorttermplanner.domain.mappers

import com.google.gson.Gson
import com.vitaliimalone.shorttermplanner.data.repository.local.models.TaskEntity
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.toIsoDateTimeString

object TaskEntityMapper {
    private val gson by lazy { Gson() }

    fun map(input: Task) = TaskEntity(
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