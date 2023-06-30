package com.example.tradeline.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.navigation.NavigationDestination

object Login : NavigationDestination {
    override val route = "login"
}

/**
 * The login screen. Has the form for users to login to their store
 */

/* The top level call for the store creation screen. */
@Composable
fun LoginScreen(
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    onLogin: () -> Unit,
    navigateToStoreCreation: () -> Unit,
    navigateToForgotPassword: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp =  navigateBack
            )
        }
    ) { innerPadding ->
        LoginBody(
            loginClick = onLogin,
            signUpClick = navigateToStoreCreation,
            forgotPasswordClick = navigateToForgotPassword,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LoginBody(loginClick: () -> Unit,
              signUpClick: () -> Unit,
              forgotPasswordClick: () -> Unit,
              modifier: Modifier = Modifier) {
    Column(
        modifier
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

        //call to the composables for the login form
        LoginForm(
            onLoginClick = loginClick,
            signUpClick = signUpClick,
            forgotPasswordClick = forgotPasswordClick
        )
    }
}

/* The Composable for
the form fields for users to login to their store */
@Composable
fun LoginForm(
    onLoginClick: () -> Unit,
    signUpClick: () -> Unit,
    forgotPasswordClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.store_name)) },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            enabled = true, //TODO
            singleLine = true
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.password)) },
            trailingIcon = { }, //TODO
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            enabled = true, //TODO
            singleLine = true
        )

        Row{
            Spacer(Modifier.weight(1f))
            Text(stringResource(R.string.forgot_password), modifier = Modifier.clickable{forgotPasswordClick()})
        }

        Button(
            onClick = {onLoginClick()},
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

        Text(stringResource(R.string.no_account_yet), modifier = Modifier.clickable{signUpClick()})
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginBody(signUpClick = {}, forgotPasswordClick = {}, loginClick = {})
}