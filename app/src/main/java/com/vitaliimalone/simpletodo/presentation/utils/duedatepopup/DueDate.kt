package com.vitaliimalone.simpletodo.presentation.utils.duedatepopup

data class DueDateItem(val type: DueDateType, val text: String)

enum class DueDateType { TODAY, TOMORROW, END_OF_WEEK, PICK }
