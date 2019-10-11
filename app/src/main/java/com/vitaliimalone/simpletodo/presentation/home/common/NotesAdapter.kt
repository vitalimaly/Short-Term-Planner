package com.vitaliimalone.simpletodo.presentation.home.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Note
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.home_note_list_item.*

class NotesAdapter(
        private val onNoteClick: (note: Note) -> Unit,
        private val onDoneClick: (note: Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    var notes = listOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_note_list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NoteViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bind(note: Note) {
            titleTextView.text = note.title
            checkImageView.setImageResource(if (note.isDone) R.drawable.ic_check_circle else 0)
            itemView.setOnClickListener { onNoteClick.invoke(note) }
            checkContainer.setOnClickListener { onDoneClick.invoke(note) }
        }
    }
}
