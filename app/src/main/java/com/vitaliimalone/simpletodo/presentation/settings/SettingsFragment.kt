package com.vitaliimalone.simpletodo.presentation.settings

import android.os.Bundle
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment(R.layout.settings_fragment) {
    private val viewModel: SettingsViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupViews()
        setupObservers()
    }

    private fun setupClickListeners() {
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupViews() {

    }

    private fun setupObservers() {

    }

}