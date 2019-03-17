package com.vitaliimalone.simpletodo.presentation.home

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.vitaliimalone.simpletodo.domain.interactors.NoteInteractor
import com.vitaliimalone.simpletodo.domain.models.Note
import com.vitaliimalone.simpletodo.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
        private val noteInteractor: NoteInteractor
) : BaseViewModel() {
    private fun createNote(title: String): Note {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val dueDate = calendar.time.time
        return Note(title = title, dueTo = dueDate)
    }

    fun addNote(title: String) {
        uiScope.launch {
            val note = createNote(title)
            noteInteractor.addNote(note)
        }
    }

    fun getAllNotes(): LiveData<PagedList<Note>> {
        return noteInteractor.getAllNotes()
    }
}
