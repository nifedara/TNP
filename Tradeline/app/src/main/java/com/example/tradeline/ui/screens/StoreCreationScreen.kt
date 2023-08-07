package com.example.tradeline.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.example.tradeline.ui.screens.viewModel.StoreCreationScreenViewModel
import com.example.tradeline.ui.screens.viewModel.StoreUiState
import com.example.tradeline.ui.screens.viewModel.UserFullDetails
import kotlinx.coroutines.launch

object CreateStore : NavigationDestination {
    override val route = "create_store"
}

//The store creation screen. Has the form fields to collect sign up data
@Composable
fun StoreCreationScreen(
    navigateToLogin: () -> Unit,
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: StoreCreationScreenViewModel = viewModel(factory = AppViewModelProvider.createFactory())
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp = navigateBack
            )
        }
    ) {
        StoreBody(
            userUiState = viewModel.storeUiState,
            onUserValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveUser()
                    navigateToLogin()
                }
            },
            navigateToLogin = navigateToLogin
        )
    }
}


@Composable
fun StoreBody(
    userUiState: StoreUiState,
    onUserValueChange: (UserFullDetails) -> Unit,
    onSaveClick: () -> Unit,
    navigateToLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        StoreInputForm(
            userDetails = userUiState.userDetails,
            onValueChange = onUserValueChange
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onSaveClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .offset(0.dp, 85.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            enabled = userUiState.isEntryValid
        ) {
            Text(stringResource(R.string.submit))
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .offset(0.dp, 80.dp), horizontalArrangement = Arrangement.Center) {
            Text(
                stringResource(R.string.have_an_account_already),
                modifier = Modifier.clickable { navigateToLogin() })
        }
    }
}

@Composable
fun StoreInputForm(
    userDetails: UserFullDetails,
    onValueChange: (UserFullDetails) -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 50.dp), horizontalArrangement = Arrangement.Start
        ) {
            Text(
                stringResource(R.string.create_store),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 55.dp), horizontalArrangement = Arrangement.Start
        ) {

            Text(
                stringResource(R.string.create_your_store),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(1.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 60.dp), horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    value = userDetails.storeName,
                    onValueChange = { onValueChange(userDetails.copy(storeName = it)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 65.dp), horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    value = userDetails.email,
                    onValueChange = { onValueChange(userDetails.copy(email = it)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = { Text(stringResource(R.string.email_address)) },
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 70.dp), horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    value = userDetails.phoneNumber,
                    onValueChange = { onValueChange(userDetails.copy(phoneNumber = it)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    label = { Text(stringResource(R.string.phone_number)) },
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 75.dp), horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    value =  userDetails.password,
                    onValueChange = { onValueChange(userDetails.copy(password = it)) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = { Text(stringResource(R.string.password)) },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible } ) {
                            Icon(
                                painter = painterResource(id = if (passwordVisible) R.drawable.eye_icon else R.drawable.invisible_eye),
                                contentDescription = "hide icon",
                                Modifier.size(16.dp)
                            )
                        }
                    },
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(0.dp, 80.dp), horizontalArrangement = Arrangement.Start
            ) {
                OutlinedTextField(
                    value = userDetails.confirmPassword,
                    onValueChange = { onValueChange(userDetails.copy(confirmPassword = it)) },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    label = { Text(stringResource(R.string.confirm_password)) },
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible } ) {
                            Icon(
                                painter = painterResource(id = if (confirmPasswordVisible) R.drawable.eye_icon else R.drawable.invisible_eye),
                                contentDescription = "hide icon",
                                Modifier.size(16.dp)
                            )
                        }
                    },
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
        }
    }
}


