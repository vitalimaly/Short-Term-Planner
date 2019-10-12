package com.vitaliimalone.simpletodo

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.vitaliimalone.simpletodo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}