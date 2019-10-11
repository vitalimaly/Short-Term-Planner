package com.vitaliimalone.simpletodo.data.repository

import com.vitaliimalone.simpletodo.data.mappers.NoteEntityMapper
import com.vitaliimalone.simpletodo.data.repository.local.NoteLocalDataSource
import com.vitaliimalone.simpletodo.domain.mappers.NoteMapper
import com.vitaliimalone.simpletodo.domain.models.Note

class NoteRepository(
        private val noteLocalDataSource: NoteLocalDataSource
) {
    suspend fun getAllNotes(): List<Note> {
        return noteLocalDataSource.getAllNotes().map(NoteMapper::map)
    }

    suspend fun addNote(note: Note) {
        noteLocalDataSource.addNote(NoteEntityMapper.map(note))
    }

    suspend fun updateNote(note: Note) {
        noteLocalDataSource.updateNote(NoteEntityMapper.map(note))
    }
}