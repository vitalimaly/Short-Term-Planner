package com.vitaliimalone.simpletodo.data.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vitaliimalone.simpletodo.data.repository.local.dao.NoteDao
import com.vitaliimalone.simpletodo.data.repository.local.models.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "noteDatabase"
    }

    abstract fun noteDao(): NoteDao
}