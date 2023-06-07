package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

/**
 * The login screen. Has the form for users to login to their store
 */

/* The top level call for the store creation screen.
It calls the "store creation form" Composable */
@Composable
fun LoginScreen(){
    Column() {

    }

    LoginForm()

    Text(text = "Text text. tHen text button")

}

/* The Composable for
the form fields for users to login to their store */
@Composable
fun LoginForm(){
    Column() {

    }

}