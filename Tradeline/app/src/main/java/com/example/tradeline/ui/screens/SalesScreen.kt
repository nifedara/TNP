package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.data.Transaction

@Composable
fun SalesScreen(
    userId: Int,
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
){
    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add icon"
                )
                Spacer(Modifier.width(2.dp))
                Text(text = "Record Sales")
            }
        }
    ){
        TransactionHeader()
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun TransactionHeader(){
    Card(
        modifier = Modifier.fillMaxWidth(), elevation = 2.dp, backgroundColor = Color(0xFFA9A9A9)
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
                text = "no product",
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
    LazyColumn() {
        items(items = itemList) { transaction ->
            Transaction(item = transaction, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun Transaction(item: Transaction, modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(painter = painterResource(R.drawable.dot_icon), contentDescription = "dot")

        Spacer(Modifier.width(1.dp))
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

@Composable
fun RecordSalesBtn(){}

@Composable
fun RecordAlertBox(){}

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