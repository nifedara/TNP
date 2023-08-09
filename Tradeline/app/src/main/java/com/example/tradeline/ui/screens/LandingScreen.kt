package com.example.tradeline.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.data.Product
import com.example.tradeline.ui.screens.viewModel.InventoryLandingViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InventoryScreen(
    userId: Int, // Add the userId parameter
    navigateToAddProduct: () -> Unit,
    navigateToRestock: () -> Unit,
    navigateToProductDetails: (Int) -> Unit,
    canNavigateBack: Boolean = false,
    modifier: Modifier = Modifier,
    viewModel: InventoryLandingViewModel = viewModel(factory = AppViewModelProvider.createFactory(userId = userId))
){

    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAddProduct() },
                shape = MaterialTheme.shapes.medium,
                containerColor = Color(0xFFE1F2CD),
                modifier = Modifier
                    .padding(20.dp)
                    .paddingFromBaseline(bottom = 150.dp) // Adjust the vertical position
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    tint = Color(0xFF2B2B85)
                )
            }
       },
        floatingActionButtonPosition = FabPosition.End, // Position the FAB in the end
        modifier = Modifier.navigationBarsPadding()
    )
    {
        innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(innerPadding).fillMaxWidth()) {
                Text(text = "PRODUCTS", color =Color(0xFF2B2B85), fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.offset(15.dp) )
            }
            InventoryBody(
                itemList = homeUiState.itemList,
                onItemClick = navigateToProductDetails,
                modifier = modifier.padding(innerPadding).fillMaxSize()
            )
        }
    }
}

@Composable
private fun InventoryBody(
    itemList: List<Product>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemList.isEmpty()) {
            Text(
                text = "no product",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.offset(0.dp, 80.dp),
                fontSize = 22.sp
            )
        } else {
            Spacer(Modifier.height(25.dp))
            InventoryList(
                itemList = itemList,
                onItemClick = { onItemClick(it.id!!) },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
private fun InventoryList(
    itemList: List<Product>, onItemClick: (Product) -> Unit, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = itemList, key = {it.id!!}) { product ->
            InventoryItem(item = product,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(product) })
        }
    }
}

@Composable
private fun InventoryItem(
    item: Product, modifier: Modifier = Modifier,
) {
//    Spacer(Modifier.height(15.dp))
//    Text(text = "PRODUCTS", color =Color(0xFF2B2B85), fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.offset(15.dp) )
//    Spacer(Modifier.height(15.dp))
    Card(
        modifier = modifier.fillMaxWidth(), elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.product_stock, item.name),
                    style = MaterialTheme.typography.h5,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF2B2B85)
                )
                Spacer(Modifier.weight(1f))

                Icons.Filled.KeyboardArrowUp


                //Text(text = item.formattedPrice(), style = MaterialTheme.typography.h4)
            }
            Text(
                text = stringResource(R.string.in_stock, item.quantity),
                style = MaterialTheme.typography.h3,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF2B2B85)
            )
        }
    }
}

