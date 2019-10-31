package com.vitaliimalone.simpletodo.domain.mappers

import com.google.gson.Gson
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import com.vitaliimalone.simpletodo.domain.models.Task
import org.threeten.bp.format.DateTimeFormatter

object TaskEntityMapper {
    private val gson by lazy { Gson() }
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    fun map(input: Task): TaskEntity {
        return TaskEntity(input.id,
                input.title,
                input.description,
                input.createdAt.format(formatter),
                input.modifiedAt.format(formatter),
                input.dueTo.format(formatter),
                input.isDone,
                input.isArchived,
                gson.toJson(input.tags),
                gson.toJson(input.subtasks))
    }
}