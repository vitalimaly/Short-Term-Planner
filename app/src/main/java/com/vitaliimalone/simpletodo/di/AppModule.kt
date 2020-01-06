package com.vitaliimalone.simpletodo.di

import androidx.room.Room
import com.vitaliimalone.simpletodo.data.repository.TaskRepository
import com.vitaliimalone.simpletodo.data.repository.local.TaskLocalDataSource
import com.vitaliimalone.simpletodo.data.repository.local.database.TasksDatabase
import com.vitaliimalone.simpletodo.domain.usecases.AddTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.DeleteTaskUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetOverdueTasksUseCase
import com.vitaliimalone.simpletodo.domain.usecases.GetTasksForHomeTabUseCase
import com.vitaliimalone.simpletodo.domain.usecases.UpdateTaskUseCase
import com.vitaliimalone.simpletodo.presentation.home.HomeViewModel
import com.vitaliimalone.simpletodo.presentation.hometab.HomeTabViewModel
import com.vitaliimalone.simpletodo.presentation.notifications.Notificator
import com.vitaliimalone.simpletodo.presentation.overdue.OverdueViewModel
import com.vitaliimalone.simpletodo.presentation.settings.SettingsViewModel
import com.vitaliimalone.simpletodo.presentation.taskdetails.TaskDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val presentation = module {
    viewModel { HomeViewModel(get()) }
    viewModel { HomeTabViewModel(get(), get()) }
    viewModel { TaskDetailsViewModel(get(), get()) }
    viewModel { SettingsViewModel() }
    viewModel { OverdueViewModel(get(), get(), get()) }
}
private val domain = module {
    single { AddTaskUseCase(get()) }
    single { UpdateTaskUseCase(get()) }
    single { GetTasksForHomeTabUseCase(get()) }
    single { DeleteTaskUseCase(get()) }
    single { GetOverdueTasksUseCase(get()) }
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