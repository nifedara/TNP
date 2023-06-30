package com.example.tradeline.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tradeline.ui.screens.*

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavRoute.Home.route
    ) {
        composable(route = BottomNavRoute.Home.route) {
            DashboardScreen(
                navigateToProfile = { navController.navigate(CreateStore.route) },
                navigateToRestock = { navController.navigate(Login.route) },
                navigateToAnalytics = { navController.navigate(Login.route) },
            )
        }
        composable(route = BottomNavRoute.Sales.route) {
            SalesScreen()
        }
        composable(route = BottomNavRoute.Inventory.route) {
            InventoryScreen(
                navigateToAddProduct = { navController.navigate(CreateStore.route) },
                navigateToRestock = { navController.navigate(Login.route) },
                navigateToProductDetails = { navController.navigate(Login.route) },
            )
        }
        composable(route = BottomNavRoute.Analytics.route) {
            AnalyticsScreen()
        }
        composable(route = BottomNavRoute.Account.route) {
            AccountScreen()
        }
        tradelineNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.tradelineNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.TRADELINE,
        startDestination = AddProduct.route
    ) {
        composable(route = AddProduct.route) {
            InventoryAddProductScreen(
                navigateBack = { navController.popBackStack() },
            )
        }
        composable(route = AddProduct.route) {
            InventoryRestockProductScreen(
                navigateBack = { navController.popBackStack() },
            )
        }
        composable(route = ProductDetails.route) {
            InventoryProductDetailsScreen(
                navigateBack = { navController.popBackStack() },
            )
            }
        }
    }
