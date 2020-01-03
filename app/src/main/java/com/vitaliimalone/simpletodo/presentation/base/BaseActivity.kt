package com.vitaliimalone.simpletodo.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.vitaliimalone.simpletodo.presentation.utils.Languages
import com.vitaliimalone.simpletodo.presentation.utils.Themes

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        Themes.setTheme(this)
        super.onCreate(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(Languages.wrapContext(newBase))
    }
}