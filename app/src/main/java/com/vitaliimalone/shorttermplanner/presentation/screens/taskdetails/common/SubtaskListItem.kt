package com.vitaliimalone.shorttermplanner.presentation.screens.taskdetails.common

import com.vitaliimalone.shorttermplanner.domain.models.Subtask

data class SubtaskListItem(var subtask: Subtask, var isFocused: Boolean = false)