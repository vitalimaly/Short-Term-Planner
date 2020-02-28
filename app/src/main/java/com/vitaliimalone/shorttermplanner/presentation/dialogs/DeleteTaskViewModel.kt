package com.vitaliimalone.shorttermplanner.presentation.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.shorttermplanner.domain.models.Task
import com.vitaliimalone.shorttermplanner.domain.usecases.DeleteTaskUseCase
import kotlinx.coroutines.launch

class DeleteTaskViewModel(
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase.deleteTask(listOf(task))
        }
    }
}