package com.vitaliimalone.simpletodo.presentation.settings.common

import android.graphics.drawable.Drawable

data class Settings(val settingsType: SettingsType, val icon: Drawable, val title: String, var subtitile: String = "")

enum class SettingsType { THEME, OVERDUE, ARCHIVE, RATE, INFO }
