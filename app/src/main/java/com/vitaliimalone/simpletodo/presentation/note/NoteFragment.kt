package com.vitaliimalone.simpletodo.presentation.note

import android.os.Bundle
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteFragment : BaseFragment(R.layout.note_fragment) {
    private val viewModel: NoteViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
