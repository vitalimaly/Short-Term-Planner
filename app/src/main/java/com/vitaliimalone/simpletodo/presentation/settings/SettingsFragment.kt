package com.vitaliimalone.simpletodo.presentation.settings

import android.os.Bundle
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.settings.common.PickThemeDialog
import com.vitaliimalone.simpletodo.presentation.settings.common.Settings
import com.vitaliimalone.simpletodo.presentation.settings.common.SettingsAdapter
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment(R.layout.settings_fragment) {
    private val viewModel: SettingsViewModel by viewModel()
    private val settingsAdapter by lazy { SettingsAdapter(this::onSettingsClick) }
    private val themeDialog by lazy { PickThemeDialog(requireActivity()) }

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
        settingsRecyclerView.adapter = settingsAdapter
    }

    private fun setupObservers() {
    }

    private fun onSettingsClick(settings: Settings) {
        when (settings) {
            Settings.THEME -> {
                themeDialog.show()
            }
            Settings.ARCHIVE -> {
            }
            Settings.INFO -> {
            }
            Settings.RATE -> {
            }
        }
    }
}