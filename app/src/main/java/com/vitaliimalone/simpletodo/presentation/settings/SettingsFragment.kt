package com.vitaliimalone.simpletodo.presentation.settings

import android.os.Bundle
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.settings.common.LanguageDialog
import com.vitaliimalone.simpletodo.presentation.settings.common.Settings
import com.vitaliimalone.simpletodo.presentation.settings.common.SettingsAdapter
import com.vitaliimalone.simpletodo.presentation.settings.common.SettingsType
import com.vitaliimalone.simpletodo.presentation.settings.common.ThemeDialog
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment(R.layout.settings_fragment) {
    private val viewModel: SettingsViewModel by viewModel()
    private val settingsAdapter by lazy { SettingsAdapter(requireContext(), this::onSettingsClick) }
    private val themeDialog by lazy { ThemeDialog(requireActivity()) }
    private val languageDialog by lazy { LanguageDialog(requireActivity()) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupViews()
        setupObservers()
    }

    private fun setupClickListeners() {
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }

    private fun setupViews() {
        toolbar.title = Res.string(R.string.settings_toolbar_title)
        settingsRecyclerView.adapter = settingsAdapter
    }

    private fun setupObservers() {
    }

    private fun onSettingsClick(settings: Settings) {
        when (settings.settingsType) {
            SettingsType.THEME -> {
                themeDialog.show()
            }
            SettingsType.LANGUAGE -> {
                languageDialog.show()
            }
            SettingsType.OVERDUE -> {
            }
            SettingsType.ARCHIVE -> {
            }
            SettingsType.INFO -> {
            }
            SettingsType.RATE -> {
            }
        }
    }
}