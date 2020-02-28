package com.vitaliimalone.shorttermplanner.presentation.screens.settings

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.vitaliimalone.shorttermplanner.R
import com.vitaliimalone.shorttermplanner.presentation.base.BaseFragment
import com.vitaliimalone.shorttermplanner.presentation.screens.settings.common.Setting
import com.vitaliimalone.shorttermplanner.presentation.screens.settings.common.SettingsAdapter
import com.vitaliimalone.shorttermplanner.presentation.utils.Res
import kotlinx.android.synthetic.main.settings_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment(R.layout.settings_fragment) {
    private val viewModel: SettingsViewModel by viewModel()
    private val settingsAdapter by lazy { SettingsAdapter(this::onSettingsClick) }

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
                val action = SettingsFragmentDirections.actionSettingsFragmentToThemeDialog()
                findNavController().navigate(action)
            }
            is Setting.Language -> {
                val action = SettingsFragmentDirections.actionSettingsFragmentToLanguageDialog()
                findNavController().navigate(action)
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
            is Setting.About -> {
                val action = SettingsFragmentDirections.actionSettingsFragmentToAboutFragment()
                findNavController().navigate(action)
            }
        }
    }
}