package com.vitaliimalone.simpletodo.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliimalone.simpletodo.domain.interactors.NoteInteractor
import com.vitaliimalone.simpletodo.domain.models.Note
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
        private val noteInteractor: NoteInteractor
) : ViewModel() {
    val notes by lazy { MutableLiveData<List<Note>>() }

    fun getAllNotes() {
        viewModelScope.launch {
            notes.value = noteInteractor.getAllNotes()
        }
    }

    fun addNote(title: String) {
        viewModelScope.launch {
            val note = createNote(title)
            noteInteractor.addNote(note)
        }
    }

    fun onDoneClick(note: Note) {
        viewModelScope.launch {
            note.isDone = !note.isDone
            noteInteractor.updateNote(note)
        }
    }

    private fun createNote(title: String): Note {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val dueDate = calendar.time.time
        return Note(title = title, dueTo = dueDate)
    }
}
