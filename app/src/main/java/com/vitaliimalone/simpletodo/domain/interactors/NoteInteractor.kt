package com.vitaliimalone.simpletodo.domain.interactors

import com.vitaliimalone.simpletodo.data.repository.NoteRepository
import com.vitaliimalone.simpletodo.domain.models.Note

class NoteInteractor(
        private val noteRepository: NoteRepository
) {
    suspend fun getAllNotes(): List<Note> {
        return noteRepository.getAllNotes()
    }

    suspend fun addNote(note: Note) {
        noteRepository.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }
}