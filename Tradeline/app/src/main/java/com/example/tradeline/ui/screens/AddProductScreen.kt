package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
){
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
        Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
            Row {
                Text(
                    text = "back",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF000000),
                    modifier = Modifier.offset(20.dp),
                )
            }
            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 0.dp), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = "Add Product",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 15.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Product Name",
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 30.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = productName,
                    onValueChange = {productName = it},
                    modifier = Modifier
                        .width(310.dp)
                        .height(50.dp)
                        .offset(10.dp, 40.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = true, //TODO
                    singleLine = true
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Product Quantity",
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 60.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = productQuantity,
                    onValueChange = {productQuantity = it},
                    modifier = Modifier
                        .width(310.dp)
                        .height(50.dp)
                        .offset(10.dp, 70.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = true, //TODO
                    singleLine = true
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Description",
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 90.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = description,
                    onValueChange = {description = it},
                    modifier = Modifier
                        .width(310.dp)
                        .height(80.dp)
                        .offset(10.dp, 100.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = true, //TODO
                    singleLine = true
                )
            }
            Row(modifier=Modifier.offset(0.dp, 120.dp) ){
                //CostPrice (Modifier.weight(1f).offset(0.dp, 0.dp).width(100.dp))
                Column(Modifier.fillMaxWidth().weight(1f).offset(0.dp, 0.dp).width(100.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Cost Price",
                            color = Color(0xFF2B2B85),
                            modifier = Modifier.offset(20.dp)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = costPrice,
                            onValueChange = {costPrice = it},
                            modifier = Modifier.width(150.dp).height(50.dp).offset(10.dp, 10.dp),
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

                //SellingPrice(Modifier.weight(1f).offset(0.dp, 0.dp).width(100.dp))
                Column(Modifier.fillMaxWidth().weight(1f).offset(0.dp, 0.dp).width(100.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Selling Price",
                            color = Color(0xFF2B2B85),
                            modifier = Modifier.offset(10.dp)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = sellingPrice,
                            onValueChange = {sellingPrice = it},
                            modifier = Modifier.width(150.dp).height(50.dp).offset(0.dp, 10.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = true, //TODO
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

            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 160.dp), horizontalArrangement = Arrangement.Center){
                Button(onClick = {
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
                    modifier = Modifier.width(320.dp).height(50.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    enabled = true //TODO
                ) {
                    Text( text = "CREATE")
                }
            }
        }
    }
}

@Composable
    fun AddProductImageCard() {
    }

    @Composable
    fun AddProductForm() {
    }


    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun AddProductPreview() {
        //AddProductBody()
    }
