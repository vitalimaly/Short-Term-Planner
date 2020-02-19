package com.vitaliimalone.simpletodo

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.jakewharton.threetenabp.AndroidThreeTen
import com.vitaliimalone.simpletodo.di.appModule
import com.vitaliimalone.simpletodo.presentation.utils.LanguageUtils
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

    override fun attachBaseContext(base: Context) = super.attachBaseContext(LanguageUtils.wrapContext(base))

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LanguageUtils.overrideLocale(this)
    }
}