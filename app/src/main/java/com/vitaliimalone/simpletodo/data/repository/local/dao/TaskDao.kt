package com.vitaliimalone.simpletodo.data.repository.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vitaliimalone.simpletodo.data.repository.local.models.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskentity WHERE isArchived = 0 AND dueTo BETWEEN :startDate AND :endDate")
    fun getUnarchivedTasksForPeriod(startDate: String, endDate: String): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT COUNT(id) FROM taskentity WHERE isArchived = 0 AND dueTo BETWEEN :startDate AND :endDate")
    fun getUnarchivedTasksCountForPeriod(startDate: String, endDate: String): Flow<Int>

    @Query("SELECT COUNT(id) FROM taskentity WHERE isArchived = 1")
    fun getArchivedTasksCount(): Flow<Int>

    @Query("SELECT * FROM taskentity WHERE isArchived = 1")
    fun getArchivedTasks(): Flow<List<TaskEntity>>
}