package com.vitaliimalone.simpletodo.data.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskentity")
    suspend fun getAllTasks(): List<TaskEntity>

    @Query("SELECT * FROM taskentity WHERE isArchived = :isArchived")
    suspend fun getAllTasks(isArchived: Boolean): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)
}