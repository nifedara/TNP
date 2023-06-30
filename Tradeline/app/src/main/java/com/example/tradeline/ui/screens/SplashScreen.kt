package com.example.tradeline.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tradeline.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(true) {
        delay(3000) // Delay for 3 seconds
        navController.navigate("home") // Navigate to the home screen
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter  = painterResource(R.drawable.app_logo),
            contentDescription = "tradeline logo",
            modifier = Modifier.size(150.dp).offset(65.dp),
            colorResource(id = R.color.blue1)
        )
        /*Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "logo",
            modifier = Modifier.size(200.dp),
            alignment = Alignment.Center,

            )*/
        Text(
            text = "TRADELINE",
            color = Color(0xFF2B2B85),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp
        )
    }
}
/*
@Composable
@Preview(showBackground = true, showSystemUi = true  )

fun AccountPreview (){
    SplashScreen()
}*/
