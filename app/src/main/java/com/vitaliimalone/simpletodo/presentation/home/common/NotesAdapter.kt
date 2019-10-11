package com.vitaliimalone.simpletodo.presentation.home.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Note
import com.vitaliimalone.simpletodo.presentation.home.HomeFragmentDirections
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.home_note_list_item.*

class NotesAdapter(
        private val onNoteClick: (note: Note) -> Unit,
        private val onDoneClick: (note: Note) -> Unit
) : PagedListAdapter<Note, NotesAdapter.NoteViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_note_list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        note?.let { holder.bind(it) }
    }

    inner class NoteViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bind(note: Note) {
            titleTextView.text = note.title
            checkImageView.setImageResource(if (note.isDone) R.drawable.ic_check_circle else 0)
            checkContainer.setOnClickListener { onDoneClick.invoke(note) }

            val action = HomeFragmentDirections.actionHomeFragmentToNoteFragment(note)
            itemView.setOnClickListener(Navigation.createNavigateOnClickListener(action.actionId, action.arguments))
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }
}
