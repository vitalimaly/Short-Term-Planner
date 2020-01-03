package com.vitaliimalone.simpletodo.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.vitaliimalone.simpletodo.presentation.utils.LanguageUtils
import com.vitaliimalone.simpletodo.presentation.utils.ThemeUtils

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtils.setTheme(this)
        super.onCreate(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageUtils.wrapContext(newBase))
    }
}