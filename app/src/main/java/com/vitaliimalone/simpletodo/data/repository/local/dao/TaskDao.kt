package com.vitaliimalone.simpletodo.data.repository.local.dao

import androidx.room.*
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskentity")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM taskentity WHERE isArchived = :isArchived")
    fun getTasks(isArchived: Boolean): Flow<List<TaskEntity>>

    @Query("SELECT * FROM taskentity WHERE isArchived = 0 AND dueTo BETWEEN :startDate AND :endDate")
    fun getUnarchivedTasksForPeriod(startDate: String, endDate: String): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}