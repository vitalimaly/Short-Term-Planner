package com.vitaliimalone.simpletodo.presentation.home.common

import com.vitaliimalone.simpletodo.domain.models.Task

data class TaskPages(
        val today: List<Task> = mutableListOf(),
        val week: List<Task> = mutableListOf(),
        val month: List<Task> = mutableListOf(),
        val todo: List<Task> = mutableListOf()
) {
    fun getByPosition(position: Int): List<Task> {
        return when (position) {
            0 -> today
            1 -> week
            2 -> month
            3 -> todo
            else -> throw IllegalArgumentException()
        }
    }

    fun size(): Int {
        return 4
    }
}