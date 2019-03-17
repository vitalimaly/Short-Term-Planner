package com.vitaliimalone.simpletodo.di

import androidx.room.Room
import com.vitaliimalone.simpletodo.data.notifications.Notificator
import com.vitaliimalone.simpletodo.data.repository.NoteRepository
import com.vitaliimalone.simpletodo.data.repository.local.NoteLocalDataSource
import com.vitaliimalone.simpletodo.data.repository.local.database.NoteDatabase
import com.vitaliimalone.simpletodo.domain.interactors.NoteInteractor
import com.vitaliimalone.simpletodo.presentation.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
}
val domainModule = module {
    single { NoteInteractor(get()) }
}
val data = module {
    single { NoteRepository(get()) }
    single { Notificator(androidContext()) }
}
val localDataSourceModule = module {
    single { NoteLocalDataSource(get()) }
    single { get<NoteDatabase>().noteDao() }
    single {
        Room.databaseBuilder(androidContext(), NoteDatabase::class.java, NoteDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
val appModule = listOf(localDataSourceModule, data, domainModule, presentationModule)