package com.vitaliimalone.simpletodo.domain.interactors

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.vitaliimalone.simpletodo.data.repository.NoteRepository
import com.vitaliimalone.simpletodo.domain.models.Note

class NoteInteractor(
        private val noteRepository: NoteRepository
) {
    fun getAllNotes(): LiveData<PagedList<Note>> {
        return noteRepository.getAllNotes()
    }

    suspend fun addNote(note: Note) {
        noteRepository.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }
}