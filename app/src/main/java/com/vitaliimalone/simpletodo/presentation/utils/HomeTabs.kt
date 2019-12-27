package com.vitaliimalone.simpletodo.presentation.utils

import com.vitaliimalone.simpletodo.R

object HomeTabs {
    val homeTabFragments = listOf(
        HomeTab(HomeTabType.TODAY, Res.string(R.string.home_tab_today)),
        HomeTab(HomeTabType.WEEK, Res.string(R.string.home_tab_week)),
        HomeTab(HomeTabType.MONTH, Res.string(R.string.home_tab_month)),
        HomeTab(HomeTabType.TODO, Res.string(R.string.home_tab_todo))
    )
}

data class HomeTab(val homeTabType: HomeTabType, val title: String)

enum class HomeTabType { TODAY, WEEK, MONTH, TODO }