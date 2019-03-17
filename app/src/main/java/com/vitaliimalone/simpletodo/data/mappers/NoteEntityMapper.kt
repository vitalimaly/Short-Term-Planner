package com.vitaliimalone.simpletodo.data.mappers

import com.vitaliimalone.simpletodo.data.repository.local.models.NoteEntity
import com.vitaliimalone.simpletodo.domain.models.Note

object NoteEntityMapper {
    fun map(input: Note): NoteEntity {
        return NoteEntity(input.id,
                input.title,
                input.description,
                input.color.ordinal,
                input.createdAt,
                input.dueTo,
                input.isDone,
                input.isArchived)
    }
}