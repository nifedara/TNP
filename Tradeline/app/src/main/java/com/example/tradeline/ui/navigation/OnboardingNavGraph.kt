package com.example.tradeline.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tradeline.ui.screens.*

fun NavGraphBuilder.onboardingNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ONBOARDING,
        startDestination = Onboarding.route
    ) {
        composable(route = Onboarding.route) {
            OnboardingScreen(
                navigateToStoreCreation = { navController.navigate(CreateStore.route) },
                navigateToLogin = { navController.navigate(Login.route) },
            )
        }
        composable(route = CreateStore.route) {
            StoreCreationScreen(
                navigateBack = { navController.popBackStack() },
                navigateToLogin = { navController.navigate(Login.route) },
//                onSubmit = {
//                    loggedUserId, loggedStore ->
//                    val destinationRoute = "${Graph.HOME}/$loggedUserId/$loggedStore"
//                    navController.navigate(destinationRoute)
//                }
            )
        }
        composable(route = Login.route) {
            LoginScreen(
                navigateBack = { navController.popBackStack() },
                navigateToStoreCreation = { navController.navigate(CreateStore.route) },
                navigateToForgotPassword = { navController.navigate(ForgotPassword.route) },
                onLogin = {
                      loggedUserId, loggedStore ->
                      val destinationRoute = "${Graph.HOME}/$loggedUserId/$loggedStore"
                      navController.navigate(destinationRoute) }
            )
        }
        composable(route = ForgotPassword.route) {
            ForgotPasswordScreen(
                navigateBack = { navController.popBackStack() },
            )
        }
    }
}
