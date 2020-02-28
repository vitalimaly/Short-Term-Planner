package com.vitaliimalone.shorttermplanner.domain.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vitaliimalone.shorttermplanner.data.repository.local.models.TaskEntity
import com.vitaliimalone.shorttermplanner.domain.models.Subtask
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.presentation.utils.extensions.toOffsetDateTime

object TaskMapper {
    private val gson by lazy { Gson() }

    fun map(input: TaskEntity) = Task(
        input.id,
        input.title,
        input.description,
        input.createdAt.toOffsetDateTime(),
        input.modifiedAt.toOffsetDateTime(),
        input.dueTo.toOffsetDateTime(),
        input.isDone,
        input.isArchived,
        gson.fromJson<MutableList<String>>(input.tags, object : TypeToken<Collection<String>>() {}.type),
        gson.fromJson<MutableList<Subtask>>(input.subtasks, object : TypeToken<Collection<Subtask>>() {}.type)
    )
}