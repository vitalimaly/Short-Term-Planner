package com.vitaliimalone.simpletodo.domain.mappers

import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import com.vitaliimalone.simpletodo.domain.models.Task
import org.threeten.bp.LocalDateTime

object TaskMapper {
    fun map(input: TaskEntity): Task {
        return Task(input.id,
                input.title,
                input.description,
                LocalDateTime.parse(input.createdAt),
                LocalDateTime.parse(input.modifiedAt),
                LocalDateTime.parse(input.dueTo),
                input.isDone,
                input.isArchived)
    }
}