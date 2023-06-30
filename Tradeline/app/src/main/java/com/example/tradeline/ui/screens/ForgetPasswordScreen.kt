package com.example.tradeline.ui.screens

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

object ForgotPassword : NavigationDestination {
    override val route = "forgot_password"
}

/**
 * The forgot password screen. Has the form for users to enter their email to regain access to their store
 */

@Composable
fun ForgotPasswordScreen(
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
){
    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = canNavigateBack,
                navigateUp =  navigateBack
            )
        }
    ) { innerPadding ->
        ForgotPasswordBody(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ForgotPasswordBody(modifier: Modifier = Modifier){
    Column(
        modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            stringResource(R.string.forgot_password2), color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        //call to the composables for the forgot password form
        ForgotForm()
    }
}

/* The Composable for
the form fields for users to login to their store */
@Composable
fun ForgotForm() {
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(stringResource(R.string.email_address)) },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            enabled = true, //TODO
            singleLine = true
        )
        Button(
            onClick = {},
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
            Text(stringResource(R.string.get_access))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview() {
    ForgotPasswordBody()
}