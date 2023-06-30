package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SalesScreen(){}

@Composable
fun SalesBody(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sales")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesSearchBar(){
    SearchBar(query = "", onQueryChange = {}, onSearch = {}, active = true, onActiveChange = {},
    leadingIcon = {}) {
        
    }
}

@Composable
fun TransactionHeader(){}

@Composable
fun TransactionList(){}

@Composable
fun RecordSalesBtn(){}

@Composable
fun RecordAlertBox(){}

@Composable
fun AuoCompleteSearchCard(){}

@Preview(showBackground = true)
@Composable
fun SalesBodyPreview() {
    SalesBody()
}