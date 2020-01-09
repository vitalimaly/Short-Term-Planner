package com.vitaliimalone.simpletodo.presentation.settings

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.settings.common.LanguageDialog
import com.vitaliimalone.simpletodo.presentation.settings.common.Settings
import com.vitaliimalone.simpletodo.presentation.settings.common.SettingsAdapter
import com.vitaliimalone.simpletodo.presentation.settings.common.ThemeDialog
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment(R.layout.settings_fragment) {
    private val viewModel: SettingsViewModel by viewModel()
    private val settingsAdapter by lazy { SettingsAdapter(this::onSettingsClick) }
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
        viewModel.overdueTasksCount.observe(viewLifecycleOwner, Observer { count ->
            val overdue = settingsAdapter.settings.find { it is Settings.Overdue } as Settings.Overdue
            overdue.count = count
            settingsAdapter.notifyDataSetChanged()
        })
    }

    private fun onSettingsClick(settings: Settings) {
        when (settings) {
            is Settings.Theme -> {
                themeDialog.show()
            }
            is Settings.Language -> {
                languageDialog.show()
            }
            is Settings.Overdue -> {
                val action = SettingsFragmentDirections.actionSettingsFragmentToOverdueFragment()
                findNavController().navigate(action)
            }
            is Settings.Archive -> {
            }
            is Settings.Rate -> {
            }
            is Settings.Info -> {
            }
        }
    }
}