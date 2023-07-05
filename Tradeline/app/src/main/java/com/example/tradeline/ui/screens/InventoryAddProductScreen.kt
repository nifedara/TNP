package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object AddProduct : NavigationDestination {
    override val route = "add_product"
//    const val userIdArg = "userId"
//    val routeWithArgs = "$route/{$userIdArg}"
}

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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                stringResource(R.string.add_product), color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(stringResource(R.string.product_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true
                )
                OutlinedTextField(
                    value = productQuantity,
                    onValueChange = { productQuantity = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(stringResource(R.string.product_quantity)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(stringResource(R.string.description)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = false,
                    maxLines = 4
                )
                OutlinedTextField(
                    value = costPrice,
                    onValueChange = { costPrice = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(stringResource(R.string.cost_price)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true
                )
                OutlinedTextField(
                    value = sellingPrice,
                    onValueChange = { sellingPrice = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(stringResource(R.string.selling_price)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true
                )
                Button(
                    onClick = { coroutineScope.launch {
                        viewModel.createNewProduct(productName,productQuantity,description,costPrice,sellingPrice,userId)
                        onCreate()
                    } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    enabled = true
                ) {
                    Text(stringResource(R.string.create))
                }
            }
        }
    }
}

//@Composable
//fun AddProductBody(){}
//
//@Composable
//fun AddProductImageCard(){}
//
//@Composable
//fun AddProductForm(){}