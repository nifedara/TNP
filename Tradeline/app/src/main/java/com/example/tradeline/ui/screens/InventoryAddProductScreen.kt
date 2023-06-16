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
import com.example.tradeline.ui.navigation.NavigationDestination

object AddProduct : NavigationDestination {
    override val route = "add_product"
}

@Composable
fun InventoryAddProductScreen(
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Inventory Add Product")
    }
}

@Composable
fun AddProductBody(){}

@Composable
fun AddProductImageCard(){}

@Composable
fun AddProductForm(){}