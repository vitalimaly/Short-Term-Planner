package com.vitaliimalone.simpletodo.presentation.screens.taskdetails.common

import com.vitaliimalone.simpletodo.domain.models.Subtask

data class SubtaskListItem(var subtask: Subtask, var isFocused: Boolean = false)