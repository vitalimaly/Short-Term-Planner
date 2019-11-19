package com.vitaliimalone.simpletodo.presentation.taskdetails.common

import com.vitaliimalone.simpletodo.domain.models.Subtask

data class SubtaskListItem(var subtask: Subtask, var isFocused: Boolean = false)