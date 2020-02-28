package com.vitaliimalone.shorttermplanner.di

import androidx.room.Room
import com.vitaliimalone.shorttermplanner.data.repository.TaskRepository
import com.vitaliimalone.shorttermplanner.data.repository.local.TaskLocalDataSource
import com.vitaliimalone.shorttermplanner.data.repository.local.database.TasksDatabase
import com.vitaliimalone.shorttermplanner.domain.usecases.AddTasksUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.DeleteArchivedTasksUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.DeleteTaskUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.GetArchivedTasksCountUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.GetArchivedTasksUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.GetTasksForHomeTabUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.GetUnarchivedOverdueTasksCountUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.GetUnarchivedOverdueTasksUseCase
import com.vitaliimalone.shorttermplanner.domain.usecases.UpdateTaskUseCase
import com.vitaliimalone.shorttermplanner.presentation.dialogs.DeleteTaskViewModel
import com.vitaliimalone.shorttermplanner.presentation.dialogs.addnewtask.AddNewTaskViewModel
import com.vitaliimalone.shorttermplanner.presentation.notifications.Notificator
import com.vitaliimalone.shorttermplanner.presentation.screens.archive.ArchiveViewModel
import com.vitaliimalone.shorttermplanner.presentation.screens.home.HomeViewModel
import com.vitaliimalone.shorttermplanner.presentation.screens.hometab.HomeTabViewModel
import com.vitaliimalone.shorttermplanner.presentation.screens.overdue.OverdueViewModel
import com.vitaliimalone.shorttermplanner.presentation.screens.settings.SettingsViewModel
import com.vitaliimalone.shorttermplanner.presentation.screens.taskdetails.TaskDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val presentation = module {
    viewModel { HomeViewModel() }
    viewModel { HomeTabViewModel(get(), get()) }
    viewModel { TaskDetailsViewModel(get()) }
    viewModel { SettingsViewModel(get(), get()) }
    viewModel { OverdueViewModel(get(), get()) }
    viewModel { ArchiveViewModel(get(), get(), get(), get(), get()) }
    viewModel { AddNewTaskViewModel(get()) }
    viewModel { DeleteTaskViewModel(get()) }
}
private val domain = module {
    single { AddTasksUseCase(get()) }
    single { UpdateTaskUseCase(get()) }
    single { GetTasksForHomeTabUseCase(get()) }
    single { DeleteTaskUseCase(get()) }
    single { GetUnarchivedOverdueTasksUseCase(get()) }
    single { GetUnarchivedOverdueTasksCountUseCase(get()) }
    single { GetArchivedTasksUseCase(get()) }
    single { GetArchivedTasksCountUseCase(get()) }
    single { DeleteArchivedTasksUseCase(get()) }
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