package com.vitaliimalone.simpletodo.domain.mappers

import com.vitaliimalone.simpletodo.data.repository.local.models.NoteEntity
import com.vitaliimalone.simpletodo.domain.models.Note

object NoteMapper {
    fun map(input: NoteEntity): Note {
        return Note(input.id,
                input.title,
                input.description,
                Note.Color.values()[input.color],
                input.createdAt,
                input.dueTo,
                input.isDone,
                input.isArchived)
    }
}