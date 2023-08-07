package com.example.tradeline.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
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
    onLogout: () -> Unit,
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
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row( modifier = Modifier.fillMaxWidth().offset(0.dp, 30.dp), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = "PROFILE",
                    modifier = Modifier.height(70.dp),
                    color = Color(0xFF2B2B85),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,


                )
            }
            Box(
                modifier = Modifier.background(shape = CircleShape, color = Color.LightGray).size(90.dp).align(
                        Alignment.CenterHorizontally
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.default_store_logo),
                    contentDescription = "shop_logo",
                    modifier = Modifier.size(30.dp).height(140.dp).align(Alignment.Center),
                )
            }
            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 10.dp), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Edit logo",
                    modifier = Modifier.offset(0.dp, 10.dp),
                    color = Color(0xFF2B2B85)
                )
            }
            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 60.dp), horizontalArrangement = Arrangement.Start) {
                OutlinedTextField(
                    value = userAccount.storeName,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.store_name)) },
                    modifier = Modifier.fillMaxWidth(),
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

            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 80.dp), horizontalArrangement = Arrangement.Start){
                OutlinedTextField(
                    value = userAccount.phoneNumber,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.phone_number)) },
                    modifier = Modifier.fillMaxWidth(),
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

            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 100.dp), horizontalArrangement = Arrangement.Start){
                OutlinedTextField(
                    value = userAccount.email,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.email_address)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = false,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledBorderColor = Color(0xFF2B2B85),
                        disabledTextColor = Color(0xFF2B2B85),
                        disabledPlaceholderColor = Color(0xFF2B2B85),
                        disabledLabelColor = Color(0xFF2B2B85)
                        ),

                )
            }

            Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 130.dp), horizontalArrangement = Arrangement.Start){
                Text(
                    text = "LOGOUT",
                    color = Color(0xFFFF6B3D),
                    modifier = Modifier.clickable { onLogout() },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}