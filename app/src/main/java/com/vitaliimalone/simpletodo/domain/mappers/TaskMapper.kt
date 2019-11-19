package com.vitaliimalone.simpletodo.domain.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import com.vitaliimalone.simpletodo.domain.models.Subtask
import com.vitaliimalone.simpletodo.domain.models.Task
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

object TaskMapper {
    private val gson by lazy { Gson() }
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    fun map(input: TaskEntity): Task {
        return Task(input.id,
                input.title,
                input.description,
                formatter.parse(input.createdAt, OffsetDateTime::from),
                formatter.parse(input.modifiedAt, OffsetDateTime::from),
                formatter.parse(input.dueTo, OffsetDateTime::from),
                input.isDone,
                input.isArchived,
                gson.fromJson<MutableList<String>>(input.tags, object : TypeToken<Collection<String>>() {}.type),
                gson.fromJson<MutableList<Subtask>>(input.subtasks, object : TypeToken<Collection<Subtask>>() {}.type))
    }
}