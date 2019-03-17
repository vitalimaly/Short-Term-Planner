package com.vitaliimalone.simpletodo.presentation.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.domain.models.Note
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.home.common.NotesAdapter
import com.vitaliimalone.simpletodo.presentation.utils.showAddNoteDialog
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.home_fragment) {
    override val viewModel: HomeViewModel by viewModel()
    private val notesAdapter by lazy { NotesAdapter() }

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
        addFab.setOnClickListener { showAddNoteDialog { viewModel.addNote(it) } }
    }

    private fun setupViews() {
        notesRecyclerView.adapter = notesAdapter
    }
}
