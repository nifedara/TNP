package com.example.tradeline.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object CreateStore : NavigationDestination {
    override val route = "create_store"
}

/**
 * The store creation screen. Has the form fields to collect sign up data
 */

/** The top level call for the store creation screen.
It calls the "store creation body" and
"store creation form" Composables */
@Composable
fun StoreCreationScreen(
    onSubmit: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: StoreCreationScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp =  navigateBack
            )
        }
    ) { innerPadding ->
        StoreCreationBody(
            newUserUiState = viewModel.newUserUiState,
            onUserValueChange = viewModel::updateUiState,
            loginClick = navigateToLogin,
            //onStoreCreationSubmit = onSubmit,
            onStoreCreationSubmit = {
                coroutineScope.launch {
                    viewModel.saveUser()
                    onSubmit()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun StoreCreationBody(
    newUserUiState: NewUserUiState,
    onUserValueChange: (UserDetails) -> Unit,
    loginClick: () -> Unit,
    onStoreCreationSubmit: () -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.create_store), color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )
        //Spacer(modifier.height(2.dp))
        Text(
            stringResource(R.string.create_your_store), color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleSmall
        )
        //Spacer(modifier.height(8.dp))

        //call to the composables for the form fields
        StoreCreationForm(
            userDetails = newUserUiState.userDetails,
            onValueChange = onUserValueChange,
            loginClick = loginClick,
            onStoreCreationSubmit = onStoreCreationSubmit)
    }
}

/* The Composable for
the form fields to collect sign up data */
@Composable
fun StoreCreationForm(
    userDetails: UserDetails,
    onValueChange: (UserDetails) -> Unit = {},
    loginClick: () -> Unit,
    onStoreCreationSubmit: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = userDetails.storeName,
            onValueChange = {onValueChange(userDetails.copy(storeName = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(stringResource(R.string.store_name)) },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            enabled = true, //TODO
            singleLine = true
        )
        OutlinedTextField(
            value = userDetails.email,
            onValueChange = {onValueChange(userDetails.copy(email = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(stringResource(R.string.email_address)) },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            enabled = true, //TODO
            singleLine = true
        )
        OutlinedTextField(
            value = userDetails.phoneNumber,
            onValueChange = {onValueChange(userDetails.copy(phoneNumber = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            label = { Text(stringResource(R.string.phone_number)) },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            enabled = true, //TODO
            singleLine = true
        )
        OutlinedTextField(
            value = userDetails.password,
            onValueChange = {onValueChange(userDetails.copy(password = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(stringResource(R.string.password)) },
            trailingIcon = { }, //TODO
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            enabled = true, //TODO
            singleLine = true
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.confirm_password)) },
            trailingIcon = { }, //TODO
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            enabled = true, //TODO
            singleLine = true
        )
        Button(
            onClick = {onStoreCreationSubmit()},
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
            Text(stringResource(R.string.submit))
        }

        Text(stringResource(R.string.have_an_account_already), modifier = Modifier.clickable{loginClick()})
    }

}

@Preview(showBackground = true)
@Composable
fun StoreCreationPreview() {
    //StoreCreationBody(loginClick = {}, onStoreCreationSubmit = {})
}