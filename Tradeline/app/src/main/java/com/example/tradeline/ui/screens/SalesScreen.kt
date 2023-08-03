package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.data.Product
import com.example.tradeline.data.Transaction
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.screens.viewModel.SalesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.DateFormat
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
                text = { Text(text = "Record Sales")},
                onClick = { salesDialogRequested = true },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "" )
                },
                modifier = Modifier
                    .padding(20.dp)
                    .paddingFromBaseline(bottom = 150.dp))
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
                SalesBottomSheet(navigateBack = navigateBack,
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
                color = Color(0xFF2B2B85)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Product",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF2B2B85)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Quantity",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF2B2B85)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Price",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.h6,
                color = Color(0xFF2B2B85)
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
                style = MaterialTheme.typography.h4
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
            Transaction(item = transaction, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun Transaction(item: Transaction, modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(painter = painterResource(R.drawable.dot_icon), contentDescription = "dot")

        Spacer(modifier.width(1.dp))
        Text(
            text = item.date,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color(0xFF2B2B85)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = item.product,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color(0xFF2B2B85)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = item.quantity.toString(),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color(0xFF2B2B85)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = item.price.toString(),
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color(0xFF2B2B85)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SalesBottomSheet(navigateBack: () -> Unit, options: List<String>, productInfo: Product?,
                             viewModel: SalesViewModel,
                             coroutine: CoroutineScope,
                             userId: Int,
                             selectedProductName: String,
                             onProductSelected: (String) -> Unit){

    // Mutable state for the input fields
    var quantity by rememberSaveable { mutableStateOf(productInfo?.quantity.toString()) }
    var sellingPrice by rememberSaveable { mutableStateOf(productInfo?.sellingPrice.toString()) }

    val currentDate = DateFormat.getDateInstance().format(Date()).toString()

    LaunchedEffect(productInfo) {
        productInfo?.let {
            quantity = it.quantity.toString()
            sellingPrice = (it.sellingPrice * quantity.toDouble()).toString()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { navigateBack() })
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Product Name")
            Spacer(modifier = Modifier.height(0.dp))
            ProductDropdown(modifier = Modifier.offset(y = 0.dp), options = options, onProductSelected)

            Spacer(modifier = Modifier.height(90.dp))

            Text(text = "Quantity", color = Color(0xFF2B2B85))
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField( value = quantity, onValueChange = { quantity=it }, modifier = Modifier.height(50.dp).fillMaxWidth(), enabled = true)

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Amount", color = Color(0xFF2B2B85))
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField( value = sellingPrice, onValueChange = { sellingPrice = it }, modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = false,
                leadingIcon = { Icon(
                    painter = painterResource(R.drawable.naira_icon),
                    contentDescription = "naira icon",
                    modifier = Modifier.padding(top = 5.2.dp))},
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { coroutine.launch {
                viewModel.updateProduct(updatedQuantity = quantity.toIntOrNull() ?: 0)
                viewModel.insertTransaction(date = currentDate, product = selectedProductName, quantity = quantity.toIntOrNull() ?: 0,
                    price = sellingPrice.toDoubleOrNull() ?: 0.0, userId = userId)
            } },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "SUBMIT")
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SalesSearchBar(){
//    SearchBar(query = "", onQueryChange = {}, onSearch = {}, active = true, onActiveChange = {},
//        leadingIcon = {}) {
//
//    }
//}

@Preview(showBackground = true)
@Composable
fun SalesBodyPreview() {
    TransactionHeader()
}