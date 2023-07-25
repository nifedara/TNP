package com.example.tradeline.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.navigation.NavigationDestination

//defines the screen's route
object Onboarding : NavigationDestination {
    override val route = "onboard"
    //const val userId = "userId"
}

/**
 * The onboarding screen. Has the "Create store" and "Login" buttons
 */


/** The top level call for the onboarding screen.
It calls the OnboardingBody composable */
@Composable
fun OnboardingScreen(
    navigateToStoreCreation: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = false,
            )
        }
    ) { innerPadding ->
        OnboardingBody(
            createStoreClick = navigateToStoreCreation,
            loginClick = navigateToLogin,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun OnboardingBody(
    createStoreClick: () -> Unit,
    loginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 118.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter  = painterResource(R.drawable.app_logo),
                contentDescription = "tradeline logo",
                modifier.size(26.dp),
                colorResource(id = R.color.blue1)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(55.dp))

        //the onboarding page image
        Image(
            painter =  painterResource(R.drawable.onboarding_image),
            contentDescription = "onboarding image",
            modifier
                .height(182.dp)
                .width(212.dp)
        )

        Spacer(modifier = Modifier.height(70.dp))

        Button(
            onClick = {createStoreClick()},
            modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(stringResource(R.string.create_store))
            Spacer(modifier.width(16.dp))
            Icon(
                painter = painterResource(R.drawable.create_store_button_icon),
                contentDescription = "create store button icon",
                modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { loginClick() },
            modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(stringResource(R.string.log_in))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        OnboardingBody(
            createStoreClick = {},
            loginClick = {}
        )
    }
}

