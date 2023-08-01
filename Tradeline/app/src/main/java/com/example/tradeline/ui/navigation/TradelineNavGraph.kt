package com.example.tradeline.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.tradeline.ui.screens.*

@Composable
fun BottomNavGraph(navController: NavHostController, userId:Int, storeName:String) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomNavRoute.Home.route
    ) {
        composable(route = BottomNavRoute.Home.route) {
            DashboardScreen(
                navigateToProfile = { navController.navigate(BottomNavRoute.Account.route) },
                navigateToRestock = { navController.navigate(Restock.route) },
                navigateToAnalytics = { navController.navigate(BottomNavRoute.Analytics.route) },
                storeName = storeName
            )
        }
        composable(route = BottomNavRoute.Sales.route) {
            SalesScreen(
                navigateBack = { navController.popBackStack() },
                userId = userId,
            )
        }
        composable( route = BottomNavRoute.Inventory.route,
        ) {
            InventoryScreen(
                navigateToAddProduct = { navController.navigate(AddProduct.route) },
                navigateToRestock = { navController.navigate(Restock.route) },
                navigateToProductDetails = { navController.navigate("${ProductDetails.route}/${it}") },
                userId = userId
            )
        }
        composable(route = BottomNavRoute.Analytics.route) {
            AnalyticsScreen()
        }
        composable(route = BottomNavRoute.Account.route) {
            AccountScreen(storeName = storeName)
        }
        tradelineNavGraph(navController = navController, userId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.tradelineNavGraph(navController: NavHostController, userId: Int) {
    navigation(
        route = Graph.TRADELINE,
        startDestination = AddProduct.route
    ) {
        composable(route = AddProduct.route) {
            InventoryAddProductScreen(
                navigateBack = { navController.popBackStack() },
                onCreate = {navController.popBackStack()},
                userId = userId
            )
        }
        composable(route = Restock.route) {
            InventoryRestockProductScreen(
                userId = userId,
                navigateBack = { navController.popBackStack() },
            )
        }
        composable(route = "${ProductDetails.route}/{itemId}")
         {
             val itemId = it.arguments?.getString("itemId")?.toInt() ?: 0
             InventoryProductDetailsScreen(
                 itemId = itemId,
                 //userId = userId,
                 navigateBack = { navController.popBackStack() },
             )
        }
    }
}

