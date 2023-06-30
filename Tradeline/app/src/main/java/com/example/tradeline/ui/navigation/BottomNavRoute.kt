package com.example.tradeline.ui.navigation

import com.example.tradeline.R

sealed class BottomNavRoute(
    val route: String,
    val title: String,
    val icon: Int
){
    object Home : BottomNavRoute(
        route = "home",
        title = "home",
        icon = R.drawable.nav_home_icon
    )

    object Sales : BottomNavRoute(
        route = "sales",
        title = "sales",
        icon = R.drawable.nav_sales_icon
    )

    object Inventory : BottomNavRoute(
        route = "inventory",
        title = "inventory",
        icon = R.drawable.nav_inventory_icon
    )
    object Analytics : BottomNavRoute(
        route = "analytics",
        title = "analytics",
        icon = R.drawable.nav_analytics_icon
    )

    object Account : BottomNavRoute(
        route = "account",
        title = "account",
        icon = R.drawable.nav_account_icon
    )
}