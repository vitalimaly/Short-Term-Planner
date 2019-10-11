package com.vitaliimalone.simpletodo.presentation.home

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Note
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.home.common.NotesAdapter
import com.vitaliimalone.simpletodo.presentation.utils.DateUtils
import com.vitaliimalone.simpletodo.presentation.utils.forceShowKeyboard
import kotlinx.android.synthetic.main.home_fragment.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.home_fragment) {
    private val viewModel: HomeViewModel by viewModel()
    private val notesAdapter by lazy { NotesAdapter(this::onNoteClick, this::onDoneClick) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()
        setupClickListeners()
        setupViews()
    }

    private fun setupObservers() {
        viewModel.getAllNotes().observe(viewLifecycleOwner, Observer<PagedList<Note>?> {
            notesAdapter.submitList(it)
        })
    }

    private fun setupClickListeners() {
        addFab.setOnClickListener {
            forceShowKeyboard()
            titleEditText.postDelayed({ titleEditText?.requestFocus() }, 1000)
        }
        sendButton.setOnClickListener { viewModel.addNote(titleEditText.text.toString()) }
        KeyboardVisibilityEvent.setEventListener(requireActivity()) {
            if (it) addFab.hide() else addFab.show()
            addNoteContainer.isVisible = it
        }
    }

    private fun setupViews() {
        notesRecyclerView.adapter = notesAdapter
        notesRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        dateTextView.text = DateUtils.getFullCurrentDate()
    }

    private fun onNoteClick(note: Note) {

    }

    private fun onDoneClick(note: Note) {
        viewModel.onDoneClick(note)
    }
}
