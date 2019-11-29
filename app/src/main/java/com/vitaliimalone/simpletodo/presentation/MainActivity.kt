package com.vitaliimalone.simpletodo.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.utils.Pref

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Pref.theme)
        setContentView(R.layout.main_activity)
    }
}
