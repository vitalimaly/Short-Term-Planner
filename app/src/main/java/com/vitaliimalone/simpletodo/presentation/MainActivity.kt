package com.vitaliimalone.simpletodo.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Themes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Themes.setTheme(this)
        setContentView(R.layout.main_activity)
    }
}
