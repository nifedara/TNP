package com.example.tradeline.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object Login : NavigationDestination {
    override val route = "login"
}

//The login screen. Has the form for users to login to their store
@Composable
fun LoginScreen(
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    onLogin: () -> Unit,
    navigateToStoreCreation: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    viewModel: LoginScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val loginState = viewModel.loginState.value

    var storeName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp =  navigateBack
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
            Text(
                stringResource(R.string.log_in), color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                stringResource(R.string.login_to_your_store), color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                if (loginState.invalidCredentials) {
                    Text(text = "Invalid credentials", color = Color.Red)
                }

                OutlinedTextField(
                    value = storeName,
                    onValueChange = {storeName = it},
                    label = { Text(stringResource(R.string.store_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    label = { Text(stringResource(R.string.password)) },
                    trailingIcon = { }, //TODO
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    enabled = true,
                    singleLine = true
                )

                Row{
                    Spacer(Modifier.weight(1f))
                    Text(stringResource(R.string.forgot_password), modifier = Modifier.clickable{navigateToForgotPassword()})
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.login(storeName, password)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    enabled = true //TODO
                ) {
                    Text(stringResource(R.string.log_in))
                }

                Text(stringResource(R.string.no_account_yet), modifier = Modifier.clickable{navigateToStoreCreation()})
            }
        }
        if (loginState.successfulLogin) {
            onLogin()
        }
    }
}
