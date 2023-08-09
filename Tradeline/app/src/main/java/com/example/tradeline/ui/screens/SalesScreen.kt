package com.example.tradeline.ui.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.data.Product
import com.example.tradeline.ui.data.Transaction
import com.example.tradeline.ui.screens.viewModel.SalesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun SalesScreen(
    userId: Int,
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = false,
    viewModel: SalesViewModel = viewModel(factory = AppViewModelProvider.createFactory(userId = userId))
){
    val coroutine = rememberCoroutineScope()
    var salesDialogRequested by rememberSaveable { mutableStateOf(false) }

    val salesUiState by viewModel.salesUiState.collectAsState()

    //gets the one that loads the product names
    val salesDetailsUiState by viewModel.saleUiState.collectAsState()
    val productNames = salesDetailsUiState.productsName

    val productInfo by rememberUpdatedState(newValue = viewModel.currentProduct.collectAsState().value)


    var selectedProductName by remember { mutableStateOf("") }

    // This lambda function will be called when the user selects a product in the dropdown
    val onProductSelected: (String) -> Unit = {
            selectedName -> coroutine.launch { viewModel.getProduct(selectedName)
            selectedProductName = selectedName}
    }

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Record Sales", color = Color(0xFF2B2B85))},
                onClick = { salesDialogRequested = true },
                containerColor = Color(0xFFE1F2CD),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "" ,
                        tint = Color(0xFF2B2B85) )
                },
                modifier = Modifier
                    .padding(20.dp)
                    .width(270.dp)
                    .paddingFromBaseline(bottom = 110.dp))
        },
        floatingActionButtonPosition = FabPosition.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TransactionHeader()
            Spacer(Modifier.height(8.dp))
            SalesBody(itemList = salesUiState.itemList)

            if (salesDialogRequested) {
                SalesBottomSheet(onSalesSheetCancel = {salesDialogRequested = false},
                    coroutine = coroutine,
                    viewModel = viewModel,
                    userId = userId,
                    options = productNames,
                    productInfo = productInfo,
                    onProductSelected = onProductSelected,
                    selectedProductName = selectedProductName)
            }
        }
    }
}

@Composable
fun TransactionHeader(){

   Row(horizontalArrangement = Arrangement.Center) {
       Text(
           text = "Transaction History",
           color = Color(0xFFF7F9FA),
           fontWeight = FontWeight.Bold,
           fontSize = 12.sp,
           modifier = Modifier.offset(10.dp),
           textAlign = TextAlign.Start
       )

   }
    Spacer(Modifier.height(20.dp))
    Card(
        modifier = Modifier.fillMaxWidth(), elevation = 2.dp, backgroundColor = Color(0xFFE7F7FB)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Date",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF22226A),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Product",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF22226A),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Quantity",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF22226A),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Price",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF22226A),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SalesBody(
    itemList: List<Transaction>,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (itemList.isEmpty()) {
            Text(
                text = "no transaction",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.offset(0.dp, 80.dp)
            )
        } else {
            TransactionList(itemList)
        }
    }
}

@Composable
fun TransactionList(
    itemList: List<Transaction>
){
    LazyColumn {
        items(items = itemList) { transaction ->
            Transaction(item = transaction, modifier = Modifier.padding(0.dp))
        }
    }
}

@Composable
fun Transaction(item: Transaction, modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
    ) {
        Icon(painter = painterResource(R.drawable.dot_icon), contentDescription = "dot", Modifier.offset(y = 7.dp))

        Spacer(modifier.width(1.dp))
        Text(
            text = item.date,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color(0xFF2B2B85),
            fontSize = 14.sp
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = item.product,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color(0xFF2B2B85),
            fontSize = 14.sp
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = item.quantity.toString(),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color(0xFF2B2B85),
            fontSize = 14.sp
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = item.price.toString(),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color(0xFF2B2B85),
            fontSize = 14.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SalesBottomSheet(onSalesSheetCancel: () -> Unit, options: List<String>, productInfo: Product?,
                             viewModel: SalesViewModel,
                             coroutine: CoroutineScope,
                             userId: Int,
                             selectedProductName: String,
                             onProductSelected: (String) -> Unit){

    var showError by remember { mutableStateOf(false) }

    // Mutable state for the input fields
    var quantity by rememberSaveable { mutableStateOf(productInfo?.quantity.toString()) }
    var sellingPrice by rememberSaveable { mutableStateOf(productInfo?.sellingPrice.toString()) }
    var calculatedPrice by rememberSaveable { mutableStateOf("") }

    val currentDate = SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(Date())

    LaunchedEffect(productInfo) {
        productInfo?.let {
            quantity = it.quantity.toString()
            sellingPrice = it.sellingPrice.toString()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onSalesSheetCancel() })
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Show error message if quantity is 0 or more than the available quantity
            if (showError) { Text( text = "Enter a valid amount", color = Color.Red) }

            Text(text = "Product Name", color = Color(0xFF2B2B85))
            Spacer(modifier = Modifier.height(8.dp))

            Row(Modifier.fillMaxWidth()) {
                ProductDropdown(options = options, onProductSelected)
            }

            Spacer(modifier = Modifier.height(10.dp)) //50.dp

            if (showError) { Text(text = "Quantity", color = Color(0xFFE36161)) } else
            { Text(text = "Quantity", color = Color(0xFF2B2B85)) }

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField( value = quantity,
                onValueChange = { newQuantity ->
                    quantity = newQuantity
                    calculatedPrice = if (newQuantity.isEmpty() || sellingPrice.isEmpty()) {
                        ""
                    } else {
                        ((sellingPrice.toDoubleOrNull() ?: 0.0) * (newQuantity.toIntOrNull() ?: 0)).toString()}
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                enabled = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF2B2B85),
                    unfocusedBorderColor = Color(0xFF2B2B85),
                    unfocusedLabelColor = Color(0xFF2B2B85),
                    focusedLabelColor = Color(0xFF2B2B85),
                    cursorColor = Color(0xFF2B2B85))

            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Amount", color = Color(0xFF2B2B85))
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField( value = calculatedPrice, onValueChange = { }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                enabled = false,
                leadingIcon = { Icon(
                    painter = painterResource(R.drawable.naira_icon),
                    contentDescription = "naira icon",
                    modifier = Modifier.padding(top = 5.2.dp))},
                colors = OutlinedTextFieldDefaults.colors(
                    disabledBorderColor = Color(0xFF2B2B85),
                    disabledTextColor = Color(0xFF2B2B85),
                    disabledPlaceholderColor = Color(0xFF2B2B85),
                    disabledLabelColor = Color(0xFF2B2B85))
            )


            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { coroutine.launch {
                val quantityInt = quantity.toIntOrNull()
                val sellingPriceDouble = calculatedPrice.toDoubleOrNull()

                if (quantityInt == null || sellingPriceDouble == null || calculatedPrice.toDoubleOrNull() == null) { //Show error message for invalid input
                    showError = true
                } else if (quantityInt <= 0 || quantityInt > (productInfo?.quantity ?: 0)) { //Show error message if quantity is 0 or more than available quantity
                    showError = true
                } else {
                    viewModel.updateProductInsertTransaction(date = currentDate, product = selectedProductName, quantity = quantity.toIntOrNull() ?: 0,
                        price = calculatedPrice.toDoubleOrNull() ?: 0.0, userId = userId)
                    onSalesSheetCancel()
                }
            } },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "SUBMIT")

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SalesBodyPreview() {
    TransactionHeader()
}