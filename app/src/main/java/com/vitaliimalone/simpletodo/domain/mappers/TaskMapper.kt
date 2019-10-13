package com.vitaliimalone.simpletodo.domain.mappers

import com.google.gson.Gson
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import com.vitaliimalone.simpletodo.domain.models.Subtask
import com.vitaliimalone.simpletodo.domain.models.Task
import org.threeten.bp.LocalDateTime

object TaskMapper {
    private val gson by lazy { Gson() }

    fun map(input: TaskEntity): Task {
        return Task(input.id,
                input.title,
                input.description,
                LocalDateTime.parse(input.createdAt),
                LocalDateTime.parse(input.modifiedAt),
                LocalDateTime.parse(input.dueTo),
                input.isDone,
                input.isArchived,
                gson.fromJson<List<String>>(input.tags, String::class.java),
                gson.fromJson<List<Subtask>>(input.subtasks, Subtask::class.java))
    }
}