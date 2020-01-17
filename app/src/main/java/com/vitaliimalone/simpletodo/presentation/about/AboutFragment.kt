package com.vitaliimalone.simpletodo.presentation.about

import android.os.Bundle
import com.vitaliimalone.simpletodo.BuildConfig
import com.vitaliimalone.simpletodo.R
import com.vitaliimalone.simpletodo.presentation.base.BaseFragment
import com.vitaliimalone.simpletodo.presentation.utils.Res
import kotlinx.android.synthetic.main.about_fragment.*

class AboutFragment : BaseFragment(R.layout.about_fragment) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupClickListeners()
        setupViews()
    }

    private fun setupClickListeners() {
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        privacyTextView.setOnClickListener {
            // todo
        }
    }

    private fun setupViews() {
        toolbar.title = Res.string(R.string.about_title)
        versionTextView.text = Res.string(R.string.about_version, BuildConfig.VERSION_NAME)
        contactTextView.text = Res.string(R.string.about_contact)
        privacyTextView.text = Res.string(R.string.about_privacy_policy)
    }
}