package com.vitaliimalone.simpletodo

import android.app.Application
import com.vitaliimalone.simpletodo.di.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule)
    }
}