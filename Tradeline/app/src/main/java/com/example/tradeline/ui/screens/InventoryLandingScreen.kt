package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InventoryScreen(
    navigateToAddProduct: () -> Unit,
    navigateToRestock: () -> Unit,
    navigateToProductDetails: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Inventory")
    }
}

@Composable
fun InventoryBody(){}

@Composable
fun InventorySearchBar(){}

@Composable
fun ProductList(){}

@Composable
fun InventoryFAB(){}

@Composable
fun CategoryDrawer(){}

@Composable
fun InventoryEditProductBtn(){}

@Composable
fun InventoryEditProductAlertBox(){}