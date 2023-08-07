package com.example.tradeline.ui.screens

import android.annotation.SuppressLint
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
import com.example.tradeline.ui.screens.viewModel.RestockViewModel
import kotlinx.coroutines.launch

object Restock : NavigationDestination {
    override val route = "restock"
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun InventoryRestockProductScreen(
    userId: Int,
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: RestockViewModel = viewModel(factory = AppViewModelProvider.createFactory(userId = userId))
){
    val restockUiState = viewModel.restockUiState.collectAsState()
    val productNames = restockUiState.value.productsName

    val coroutine = rememberCoroutineScope()

    val productInfo by rememberUpdatedState(newValue = viewModel.currentProduct.collectAsState().value)

    // Mutable state for the input fields
    var quantity by rememberSaveable { mutableStateOf(productInfo?.quantity.toString()) }
    var description by rememberSaveable { mutableStateOf(productInfo?.description.orEmpty()) }
    var costPrice by rememberSaveable { mutableStateOf(productInfo?.costPrice.toString().orEmpty()) }
    var sellingPrice by rememberSaveable { mutableStateOf(productInfo?.sellingPrice.toString().orEmpty()) }

    LaunchedEffect(productInfo) {
        productInfo?.let {
            // Update the mutable states when productInfo changes
            quantity = it.quantity.toString()
            description = it.description
            costPrice = it.costPrice.toString()
            sellingPrice = it.sellingPrice.toString()
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp = navigateBack
            )
        }
    ){
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp))
        {
            Row( modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 0.dp), horizontalArrangement = Arrangement.Start )
            {
                Text(
                    text = "Restock",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 15.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row( modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Product Name",
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 30.dp)
                )
            }
            Row( modifier = Modifier.fillMaxWidth()
            ) {
                ProductDropdown(options = productNames){selectedName ->
                    coroutine.launch { viewModel.getProduct(selectedName) }
                }
            }

            Row( modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Product Quantity",
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 60.dp)
                )
            }
            Row( modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    modifier = Modifier
                        .width(310.dp)
                        .height(50.dp)
                        .offset(10.dp, 70.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true
                )
            }

            Row( modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Product Description",
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 60.dp)
                )
            }
            Row( modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .width(310.dp)
                        .height(50.dp)
                        .offset(10.dp, 70.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true
                )
            }

            Row(modifier=Modifier.offset(0.dp, 90.dp) ){
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .offset(0.dp, 0.dp)
                        .width(100.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Cost Price",
                            color = Color(0xFF2B2B85),
                            modifier = Modifier.offset(20.dp)
                        )
                    }
                    Row( modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = costPrice,
                            onValueChange = { costPrice = it },
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp)
                                .offset(10.dp, 10.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = true, //TODO
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

                Spacer(modifier = Modifier.width(5.dp))

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .offset(0.dp, 0.dp)
                        .width(100.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Selling Price",
                            color = Color(0xFF2B2B85),
                            modifier = Modifier.offset(10.dp)
                        )
                    }
                    Row( modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = sellingPrice,
                            onValueChange = { sellingPrice = it },
                            modifier = Modifier
                                .width(150.dp)
                                .height(50.dp)
                                .offset(0.dp, 10.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = true,
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.naira_icon),
                                    contentDescription = "naira icon",
                                    modifier = Modifier.padding(top = 5.2.dp)
                                )
                            },
                        )
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth()
                .offset(0.dp, 130.dp), horizontalArrangement = Arrangement.Center){

                Button(onClick = {
                                 coroutine.launch {
                                     viewModel.updateProduct(updatedQuantity = quantity.toIntOrNull() ?: 0, updatedDescription = description,
                                     updatedCost = costPrice.toDoubleOrNull() ?: 0.0, updatedSelling = sellingPrice.toDoubleOrNull() ?: 0.0)
                                 }
                                navigateBack()
                     },
                    modifier = Modifier.width(320.dp)
                        .height(50.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = Color(0xFFFFFFFF)),
                    enabled = true
                ) {
                    Text( text = "Restock")
                }
            }
        }
    }
}

@Composable
fun ProductDropdown(modifier: Modifier = Modifier, options: List<String>, onProductSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableStateOf(options.firstOrNull()) }

    Button(
        onClick = { expanded = true },
        //modifier = Modifier.offset(10.dp, 40.dp)
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .offset(10.dp, 70.dp)
    ) {
        Text(text = selectedOption ?: "Select a product")
        Icon(
            painter = painterResource(R.drawable.vector),
            contentDescription = "dropdown icon",
            modifier = Modifier.offset(x = 4.dp)
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                onClick = {
                    selectedOption = option
                    expanded = false
                    onProductSelected(option)
                },
                text = { Text(option) },
                modifier = Modifier
                    .width(310.dp)
                    .height(50.dp)
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultRestockPreview(){
    InventoryRestockProductScreen(navigateBack = { /*TODO*/ }, userId = 0)
}