package com.example.tradeline.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.screens.viewModel.AccountScreenViewModel

@Composable
fun AccountScreen(
    storeName: String, // store name
    viewModel: AccountScreenViewModel = viewModel(factory = AppViewModelProvider.createFactory(storeName = storeName))
) {
    val details = viewModel.accountUiState.collectAsState()
    val userAccount = details.value.userDetails

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = false
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row( modifier = Modifier.paddingFromBaseline(top = 50.dp).fillMaxWidth()) {
                Text(
                    text = "PROFILE",
                    modifier = Modifier.size(65.dp).offset(20.dp),
                    color = Color(0xFF2B2B85),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Box(
                modifier = Modifier.background(shape = CircleShape, color = Color.LightGray).size(130.dp).align(
                        Alignment.CenterHorizontally
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.default_store_logo),
                    contentDescription = "shop_logo",
                    modifier = Modifier.size(40.dp).height(142.dp).align(Alignment.Center),
                )
            }
            Text(text = "Edit logo", modifier= Modifier.offset(0.dp, 10.dp), color = Color(0xFF2B2B85))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Store Name",
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 25.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = userAccount.storeName,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.store_name)) },
                    modifier = Modifier.offset(15.dp, 25.dp).width(350.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = false,
                    singleLine = true)
            }
            Row(modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Phone Number",
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 40.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()){
                OutlinedTextField(
                    value = userAccount.phoneNumber,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.phone_number)) },
                    modifier = Modifier.width(350.dp).offset(15.dp, 40.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = false,
                    singleLine = true)
            }
            Row(modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Email Address",
                    color = Color(0xFF2B2B85),
                    modifier = Modifier.offset(20.dp, 50.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()){
                OutlinedTextField(
                    value = userAccount.email,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.email_address)) },
                    modifier = Modifier.width(350.dp).offset(15.dp, 50.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = false,
                    singleLine = true)
            }
            Row(modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Contact Help",
                    color = Color(0xFF000000),
                    modifier = Modifier.offset(20.dp, 60.dp),
                    fontSize = 18.sp,
                )
            }
            Row(modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "LOGOUT",
                    color = Color(0xFFFF6B3D),
                    modifier = Modifier.offset(20.dp, 70.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}