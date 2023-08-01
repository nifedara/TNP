package com.example.tradeline.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tradeline.TradelineApp

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.ONBOARDING
    ) {
        onboardingNavGraph(navController = navController)
        composable(route = "${Graph.HOME}/{userId}/{storeName}") { //
            val userId = remember { it.arguments?.getString("userId")?.toIntOrNull() }
            val storeName = remember { it.arguments?.getString("storeName") }

            if (userId != null && storeName != null) {
                TradelineApp(userId = userId, storeName = storeName)
            }
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val ONBOARDING = "onboarding_graph"
    const val HOME = "home_graph/{userId}/{storeName}"
    const val TRADELINE = "tradeline_graph/{userId}"
}



