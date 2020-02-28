package com.vitaliimalone.shorttermplanner.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.vitaliimalone.shorttermplanner.presentation.utils.LanguageUtils
import com.vitaliimalone.shorttermplanner.presentation.utils.ThemeUtils

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtils.setTheme(this)
        super.onCreate(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageUtils.wrapContext(newBase))
    }
}