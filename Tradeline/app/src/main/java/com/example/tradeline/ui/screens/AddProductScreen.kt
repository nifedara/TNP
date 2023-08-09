package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.navigation.NavigationDestination
import com.example.tradeline.ui.screens.viewModel.InventoryAddProductScreenViewModel
import kotlinx.coroutines.launch


object AddProduct : NavigationDestination {
    override val route = "add_product"
}

@ExperimentalMaterial3Api
@Composable
fun InventoryAddProductScreen(
    onCreate: () -> Unit,
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    userId: Int, // Add the userId parameter
    viewModel: InventoryAddProductScreenViewModel = viewModel(factory = AppViewModelProvider.createFactory())
) {
    val coroutineScope = rememberCoroutineScope()

    var productName by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var costPrice by remember { mutableStateOf("") }
    var sellingPrice by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp = navigateBack
            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 50.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Add Product",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(0.dp, 0.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 70.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Product Name",
                    color = Color(0xFF2B2B85),

                    )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 70.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2B2B85),
                        unfocusedBorderColor = Color(0xFF2B2B85),
                        unfocusedLabelColor = Color(0xFF2B2B85),
                        focusedLabelColor = Color(0xFF2B2B85),
                        cursorColor = Color(0xFF2B2B85)
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 90.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Product Quantity",
                    color = Color(0xFF2B2B85),
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 100.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    value = productQuantity,
                    onValueChange = { productQuantity = it },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2B2B85),
                        unfocusedBorderColor = Color(0xFF2B2B85),
                        unfocusedLabelColor = Color(0xFF2B2B85),
                        focusedLabelColor = Color(0xFF2B2B85),
                        cursorColor = Color(0xFF2B2B85)
                    )
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 120.dp)) {
                Text(
                    text = "Description",
                    color = Color(0xFF2B2B85),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 130.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2B2B85),
                        unfocusedBorderColor = Color(0xFF2B2B85),
                        unfocusedLabelColor = Color(0xFF2B2B85),
                        focusedLabelColor = Color(0xFF2B2B85),
                        cursorColor = Color(0xFF2B2B85)
                    )
                )
            }
            Row(modifier = Modifier.offset(0.dp, 0.dp)) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .offset(0.dp, 0.dp)
                        .width(100.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .offset(0.dp, 150.dp)) {
                        Text(
                            text = "Cost Price",
                            color = Color(0xFF2B2B85),
                        )
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .offset(0.dp, 160.dp)) {
                        OutlinedTextField(
                            value = costPrice,
                            onValueChange = { costPrice = it },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.large,
                            enabled = true,
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.naira_icon),
                                    contentDescription = "naira icon",
                                    modifier = Modifier.padding(top = 5.2.dp)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF2B2B85),
                                unfocusedBorderColor = Color(0xFF2B2B85),
                                unfocusedLabelColor = Color(0xFF2B2B85),
                                focusedLabelColor = Color(0xFF2B2B85),
                                cursorColor = Color(0xFF2B2B85)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(5.dp))

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .width(100.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .offset(0.dp, 150.dp)) {
                        Text(
                            text = "Selling Price",
                            color = Color(0xFF2B2B85),
                        )
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .offset(0.dp, 160.dp)) {
                        OutlinedTextField(
                            value = sellingPrice,
                            onValueChange = { sellingPrice = it },
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.large,
                            enabled = true,
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.naira_icon),
                                    contentDescription = "naira icon",
                                    modifier = Modifier.padding(top = 5.2.dp)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF2B2B85),
                                unfocusedBorderColor = Color(0xFF2B2B85),
                                unfocusedLabelColor = Color(0xFF2B2B85),
                                focusedLabelColor = Color(0xFF2B2B85),
                                cursorColor = Color(0xFF2B2B85)
                            )
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 190.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.createNewProduct(
                                productName,
                                productQuantity,
                                description,
                                costPrice,
                                sellingPrice,
                                userId
                            )
                            onCreate()
                        }
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    enabled = true
                ) {
                    Text(text = "CREATE")
                }
            }
        }
    }
}