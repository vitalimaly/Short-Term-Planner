package com.vitaliimalone.simpletodo.di

import androidx.room.Room
import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.data.repository.local.TaskLocalDataSource
import com.vitaliimalone.simpletodo.data.repository.local.database.TasksDatabase
import com.vitaliimalone.simpletodo.domain.usecases.AddTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetTasksUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import com.vitaliimalone.simpletodo.notifications.Notificator
import com.vitaliimalone.simpletodo.presentation.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val presentation = module {
    viewModel { HomeViewModel(get(), get(), get()) }
}
private val domain = module {
    single { GetTasksUseCase(get()) }
    single { AddTaskUseCase(get()) }
    single { UpdateTaskUseCase(get()) }
}
private val data = module {
    single { TaskRepository(get()) }
    single { Notificator(androidContext()) }
    single { TaskLocalDataSource(get()) }
    single { get<TasksDatabase>().taskDao() }
    single {
        Room.databaseBuilder(androidContext(), TasksDatabase::class.java, TasksDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
val appModule = listOf(presentation, data, domain)