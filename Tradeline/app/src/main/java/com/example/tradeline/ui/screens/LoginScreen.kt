package com.example.tradeline.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.navigation.NavigationDestination
import com.example.tradeline.ui.screens.viewModel.LoginScreenViewModel
import kotlinx.coroutines.launch


object Login : NavigationDestination {
    override val route = "login"
}

//The login screen. Has the form for users to login to their store
@Composable
fun LoginScreen(
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    onLogin: (Int, String) -> Unit, // Modified onLogin callback to pass the userId and store name
    navigateToStoreCreation: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    viewModel: LoginScreenViewModel = viewModel(factory = AppViewModelProvider.createFactory())
) {
    val coroutineScope = rememberCoroutineScope()
    val loginState = viewModel.loginState.value


    val loggedUserId = viewModel.userId
    val loggedStore = viewModel.storeName

    var storeName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        )
        {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 90.dp), horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(R.string.log_in), color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row( modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 100.dp), horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    stringResource(R.string.login_to_your_store),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                if (loginState.invalidCredentials) {
                    Text(text = "Invalid credentials", color = Color.Red)
                }
                Row( modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 110.dp), horizontalArrangement = Arrangement.Start
                ) {
                    OutlinedTextField(
                        value = storeName,
                        onValueChange = { storeName = it },
                        label = { Text(stringResource(R.string.store_name)) },
                        modifier = Modifier.fillMaxWidth(),
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
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 120.dp), horizontalArrangement = Arrangement.Start) {

                    var passwordVisibility by remember { mutableStateOf(false) }

                    val icon = if (passwordVisibility){
                        Icons.Filled.Visibility
                    }
                        /*painterResource(id = R.drawable.eye_icon)*/
                    else{
                        Icons.Filled.VisibilityOff
                    }

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(R.string.password)) },
                        trailingIcon = { IconButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        }) {
                            Icon( icon, contentDescription ="visibility icon" )
                        } },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
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

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 130.dp), horizontalArrangement = Arrangement.Start) {
                    Spacer(Modifier.weight(1f))
                    Text(
                        stringResource(R.string.forgot_password),
                        modifier = Modifier.clickable { navigateToForgotPassword() })
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.login(storeName, password)
                            loggedUserId?.let {
                                loggedStore?.let { onLogin(loggedUserId, loggedStore) }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .offset(0.dp, 140.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    enabled = true
                ) {
                    Text(stringResource(R.string.log_in))
                }
        Row(modifier = Modifier
            .fillMaxWidth()
            .offset(0.dp, 150.dp), horizontalArrangement = Arrangement.Center) {
            Text(
                stringResource(R.string.no_account_yet),
                modifier = Modifier.clickable { navigateToStoreCreation() })
        }
            }
        }
    }
}


