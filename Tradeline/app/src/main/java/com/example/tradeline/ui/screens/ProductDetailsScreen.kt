package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.navigation.NavigationDestination
import com.example.tradeline.ui.screens.viewModel.ProductDetails
import com.example.tradeline.ui.screens.viewModel.ProductDetailsScreenViewModel
import kotlinx.coroutines.launch

object ProductDetails : NavigationDestination {
    override val route = "product_details"
}

@Composable
fun InventoryProductDetailsScreen(
    itemId: Int, // Add the itemId parameter
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: ProductDetailsScreenViewModel = viewModel(factory = AppViewModelProvider.createFactory(itemId = itemId))
){
    val detailsUiState = viewModel.detailsUiState.collectAsState()
    val detail = detailsUiState.value.itemDetails
    val outOfStock = detailsUiState.value.outOfStock

    val coroutineScope = rememberCoroutineScope()

    var productName by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp = navigateBack
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center //just added
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 0.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Product Details",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(0.dp, 50.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(Modifier.height(58.dp))
//            if (outOfStock) {Text( text = "Out of Stock", color = Color.Red, textAlign = TextAlign.Center)}
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                if (outOfStock) {Text( text = "Out of Stock", color = Color.Red, textAlign = TextAlign.Start)}
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 30.dp)){

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .offset(0.dp, 15.dp)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Name",
                            color = Color(0xFF2B2B85),
                            modifier = Modifier.offset(12.dp),
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp) )

                Column(
                    Modifier
                        .weight(2f)
                        .fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = detail.name,
                            onValueChange = {detail.name = it},
                            modifier = Modifier
                                .width(200.dp)
                                .height(50.dp)
                                .offset(10.dp, 10.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = false,
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledBorderColor = Color(0xFF2B2B85),
                                disabledTextColor = Color(0xFF2B2B85),
                                disabledPlaceholderColor = Color(0xFF2B2B85),
                                disabledLabelColor = Color(0xFF2B2B85))
                            )
                    }
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 50.dp)){
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .offset(0.dp, 15.dp)) {
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

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(2f)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = detail.sellingPrice,
                            onValueChange = {},
                            modifier = Modifier
                                .width(200.dp)
                                .height(50.dp)
                                .offset(10.dp, 10.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = false,
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.naira_icon),
                                    contentDescription = "naira icon",
                                    modifier = Modifier.padding(top = 5.2.dp)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledBorderColor = Color(0xFF2B2B85),
                                disabledTextColor = Color(0xFF2B2B85),
                                disabledPlaceholderColor = Color(0xFF2B2B85),
                                disabledLabelColor = Color(0xFF2B2B85))
                            )

                    }
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 80.dp)){
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .offset(0.dp, 15.dp)) {
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

                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(2f)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = detail.description,
                            onValueChange = {},
                            modifier = Modifier
                                .width(200.dp)
                                .height(80.dp)
                                .offset(10.dp, 10.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = false,
                            singleLine = false,
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledBorderColor = Color(0xFF2B2B85),
                                disabledTextColor = Color(0xFF2B2B85),
                                disabledPlaceholderColor = Color(0xFF2B2B85),
                                disabledLabelColor = Color(0xFF2B2B85))
                            )
                    }
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 120.dp),
                horizontalArrangement = Arrangement.Center){

                var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
                var editDialogRequested by rememberSaveable { mutableStateOf(false) }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .offset(0.dp, 15.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {

                        Button(onClick = { editDialogRequested = true },
                            modifier = Modifier.fillMaxWidth()
                                .height(50.dp),
                            shape = MaterialTheme.shapes.large,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                contentColor = Color(0xFFFFFFFF)),
                            enabled = true
                        )
                        {Text( text = "EDIT")}
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        TextButton(onClick = {  deleteConfirmationRequired = true },modifier = Modifier.fillMaxWidth()
                            .height(50.dp),
                            shape = MaterialTheme.shapes.large,
                            enabled = true,
                        ) {

                            Text( text = "DELETE", color = Color(0xFFE36161))
                        }
                    }
                    if (deleteConfirmationRequired) {
                        DeleteConfirmationDialog(
                            onDeleteConfirm = {
                                deleteConfirmationRequired = false
                                coroutineScope.launch {
                                    viewModel.deleteProduct()
                                    navigateBack()
                                }
                            },
                            onDeleteCancel = { deleteConfirmationRequired = false }
                        )
                    }
                    if (editDialogRequested) {
                        EditBottomSheet(detail = detail,
                            onEditCancel  = {editDialogRequested = false}
                        ) { updatedProductName, updatedDescription ->
                            coroutineScope.launch {
                                productName = updatedProductName // Update mutable state variables
                                description = updatedDescription

                                viewModel.updateProduct(updatedProductName = productName, updatedDescription = description)
                                navigateBack()
                            }
                        }
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
                Text(text = "No") }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditBottomSheet(
    detail: ProductDetails,
    onEditCancel: () -> Unit,
    onEditClick: (updatedProductName: String, updatedDescription: String) -> Unit,
){

    var productName by rememberSaveable { mutableStateOf(detail.name) }
    var description by rememberSaveable { mutableStateOf(detail.description) }

    ModalBottomSheet(
        onDismissRequest = { onEditCancel() })
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Product Name", color =Color(0xFF2B2B85))
            Spacer(modifier = Modifier.height(3.dp))
            OutlinedTextField(value = productName,
                onValueChange = {productName = it},
                enabled = true,
                modifier = Modifier.fillMaxWidth(), colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2B2B85),
                    unfocusedBorderColor = Color(0xFF2B2B85),
                    unfocusedLabelColor = Color(0xFF2B2B85),
                    focusedLabelColor = Color(0xFF2B2B85),
                    cursorColor = Color(0xFF2B2B85))
                )

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Description", color =Color(0xFF2B2B85) )
            Spacer(modifier = Modifier.height(3.dp))
            OutlinedTextField(value = description,
                onValueChange = {description = it},
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2B2B85),
                    unfocusedBorderColor = Color(0xFF2B2B85),
                    unfocusedLabelColor = Color(0xFF2B2B85),
                    focusedLabelColor = Color(0xFF2B2B85),
                    cursorColor = Color(0xFF2B2B85),
               )
                )

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { onEditClick(productName, description) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "EDIT")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultDetailsPreview(){
    InventoryProductDetailsScreen(navigateBack = { /*TODO*/ }, itemId = 0)
}