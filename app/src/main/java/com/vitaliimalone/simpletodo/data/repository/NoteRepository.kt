package com.vitaliimalone.simpletodo.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.vitaliimalone.simpletodo.data.mappers.NoteEntityMapper
import com.vitaliimalone.simpletodo.data.repository.local.NoteLocalDataSource
import com.vitaliimalone.simpletodo.domain.mappers.NoteMapper
import com.vitaliimalone.simpletodo.domain.models.Note

class NoteRepository(
        private val noteLocalDataSource: NoteLocalDataSource
) {
    suspend fun addNote(note: Note) {
        noteLocalDataSource.addNote(NoteEntityMapper.map(note))
    }

    fun getAllNotes(): LiveData<PagedList<Note>> {
        return noteLocalDataSource.getAllNotes().map(NoteMapper::map).toLiveData(50)
    }
}