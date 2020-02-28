package com.vitaliimalone.shorttermplanner.data.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vitaliimalone.shorttermplanner.data.repository.local.dao.TaskDao
import com.vitaliimalone.shorttermplanner.data.repository.local.models.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "TasksDatabase"
    }

    abstract fun taskDao(): TaskDao
}