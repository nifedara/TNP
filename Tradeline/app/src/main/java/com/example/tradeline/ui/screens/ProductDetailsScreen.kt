package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.navigation.NavigationDestination
import com.example.tradeline.ui.screens.viewModel.ProductDetailsScreenViewModel

object ProductDetails : NavigationDestination {
    override val route = "product_details"
}

@Composable
fun InventoryProductDetailsScreen(
    itemId: Int, // Add the itemId parameter
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: ProductDetailsScreenViewModel = viewModel(factory = AppViewModelProvider.createFactory(itemId))
){
    val detailsUiState = viewModel.detailsUiState.collectAsState()
    val detail = detailsUiState.value.itemDetails

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp = navigateBack
            )
        }
    ){
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().offset(0.dp, 0.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Product Details",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(0.dp, 15.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 40.dp)){

                //ProductName(Modifier.weight(1f).offset(0.dp, 15.dp) )
                Column(
                    Modifier.fillMaxWidth().weight(1f).offset(0.dp, 15.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Product Name",
                            color = Color(0xFF2B2B85),
                            modifier = Modifier.offset(12.dp),
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp) )
                //ProductNameDetails(Modifier.weight(2f))
                Column(Modifier.weight(2f).fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = detail.name,
                            onValueChange = {detail.name = it},
                            modifier = Modifier.width(200.dp).height(40.dp).offset(10.dp, 10.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = false,
                            singleLine = true)
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 80.dp)){
                //ProductPrice(Modifier.weight(1f).offset(0.dp, 15.dp) )
                Column(Modifier.fillMaxWidth().weight(1f).offset(0.dp, 15.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Selling Price",
                            color = Color(0xFF2B2B85),
                            modifier = Modifier.offset(12.dp),
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp) )
                //ProductPriceDetails(Modifier.weight(2f))
                Column(Modifier.fillMaxWidth().weight(2f)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = detail.sellingPrice,
                            onValueChange = {},
                            modifier = Modifier.width(200.dp).height(40.dp).offset(10.dp, 10.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = false,
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.naira_icon),
                                    contentDescription = "naira icon",
                                    modifier = Modifier.padding(top = 5.2.dp)
                                )
                            }
                        )
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 120.dp)){
                //ProductDescription(Modifier.weight(1f).offset(0.dp, 15.dp))
                Column(
                    Modifier.fillMaxWidth().weight(1f).offset(0.dp, 15.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Description",
                            color = Color(0xFF2B2B85),
                            modifier = Modifier.offset(12.dp),
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp) )
                //ProductDescriptionDetails(Modifier.weight(2f))
                Column(Modifier.fillMaxWidth().weight(2f)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = detail.description,
                            onValueChange = {},
                            modifier = Modifier.width(200.dp).height(80.dp).offset(10.dp, 10.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = false,
                            singleLine = false,)
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 180.dp),
                horizontalArrangement = Arrangement.Center){

                var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

                //DeleteButton(Modifier.weight(2f).offset(0.dp, 15.dp) )
                Column(Modifier.fillMaxWidth().weight(2f).offset(0.dp, 15.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {

                        Button(
                            onClick = { deleteConfirmationRequired = true },
                            modifier = Modifier.width(120.dp).height(50.dp),
                            shape = MaterialTheme.shapes.large,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFFFFF),
                                contentColor = Color(0xFFE36161)),
                            enabled = true //TODO
                        )
                        {Text( text = "DELETE")}
                    }
                }
                Spacer(modifier = Modifier.width(5.dp) )
                //EditButton(Modifier.weight(2f).offset(0.dp, 15.dp))
                Column(Modifier.fillMaxWidth().weight(2f).offset(0.dp, 15.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {

                        Button(onClick = { /*TODO*/ },
                            modifier = Modifier.width(120.dp).height(50.dp),
                            shape = MaterialTheme.shapes.large,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                contentColor = Color(0xFFFFFFFF)),
                            enabled = true //TODO
                        )
                        {Text( text = "EDIT")}
                    }
                    if (deleteConfirmationRequired) {
                        DeleteConfirmationDialog(
                            onDeleteConfirm = {
                                deleteConfirmationRequired = false
                                //onDelete()
                            },
                            onDeleteCancel = { deleteConfirmationRequired = false }
                        )
                    }
                }

            }
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text("???") },
        text = { Text("Are you sure you want to delete?") },
        modifier = modifier.padding(16.dp),
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                androidx.compose.material.Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultDetailsPreview(){
    InventoryProductDetailsScreen(navigateBack = { /*TODO*/ }, itemId = 0)
}