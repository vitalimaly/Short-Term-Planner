package com.vitaliimalone.simpletodo.presentation.views.duedatepopup

// todo maybe change to enum
data class DueDateItem(val type: DueDateType, val text: String)

enum class DueDateType { TODAY, TOMORROW, END_OF_WEEK, PICK }
