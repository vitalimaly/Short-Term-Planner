package com.vitaliimalone.simpletodo.domain.mappers

import com.google.gson.Gson
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import com.vitaliimalone.simpletodo.domain.models.Task

object TaskEntityMapper {
    private val gson by lazy { Gson() }

    fun map(input: Task): TaskEntity {
        return TaskEntity(input.id,
                input.title,
                input.description,
                input.createdAt.toString(),
                input.modifiedAt.toString(),
                input.dueTo.toString(),
                input.isDone,
                input.isArchived,
                gson.toJson(input.tags),
                gson.toJson(input.subtasks))
    }
}