package com.vitaliimalone.simpletodo.presentation.settings

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.settings.common.LanguageDialog
import com.vitaliimalone.simpletodo.presentation.settings.common.Setting
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
            val overdue = settingsAdapter.settings.find { it is Setting.Overdue } as Setting.Overdue
            overdue.count = count
            settingsAdapter.notifyDataSetChanged()
        })
        viewModel.archivedTasksCount.observe(viewLifecycleOwner, Observer { count ->
            val archived = settingsAdapter.settings.find { it is Setting.Archive } as Setting.Archive
            archived.count = count
            settingsAdapter.notifyDataSetChanged()
        })
    }

    private fun onSettingsClick(setting: Setting) {
        when (setting) {
            is Setting.Theme -> {
                themeDialog.show()
            }
            is Setting.Language -> {
                languageDialog.show()
            }
            is Setting.Overdue -> {
                val action = SettingsFragmentDirections.actionSettingsFragmentToOverdueFragment()
                findNavController().navigate(action)
            }
            is Setting.Archive -> {
                val action = SettingsFragmentDirections.actionSettingsFragmentToArchiveFragment()
                findNavController().navigate(action)
            }
            is Setting.Rate -> {
                val action = SettingsFragmentDirections.actionSettingsFragmentToAppPlayStorePage()
                findNavController().navigate(action)
            }
            is Setting.Info -> {
            }
        }
    }
}