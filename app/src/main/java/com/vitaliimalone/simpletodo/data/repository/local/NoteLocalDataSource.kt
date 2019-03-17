package com.vitaliimalone.simpletodo.data.repository.local

import androidx.paging.DataSource
import com.vitaliimalone.simpletodo.data.repository.local.dao.NoteDao
import com.vitaliimalone.simpletodo.data.repository.local.models.NoteEntity

class NoteLocalDataSource(
        private val noteDao: NoteDao
) {
    suspend fun addNote(noteEntity: NoteEntity) {
        noteDao.addNote(noteEntity)
    }

    fun getAllNotes(): DataSource.Factory<Int, NoteEntity> {
        return noteDao.getAllNotes()
    }
}