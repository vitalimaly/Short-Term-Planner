package com.vitaliimalone.simpletodo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.models.Task
import com.vitaliimalone.simpletodo.domain.usecases.AddTaskUseCase
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class HomeViewModel(
        private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {
    var testPlusDays = 0L

    fun addNewTestTask() {
        viewModelScope.launch {
            addTaskUseCase.addTask(Task(dueTo = OffsetDateTime.now().plusDays(testPlusDays++)))
        }
    }
}
