package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

/**
 * The store creation screen. Has the form fields to collect sign up data
 */

/* The top level call for the store creation screen.
It calls the "store creation form" Composable */
@Composable
fun StoreCreationScreen(){
    Column() {
        
    }

    StoreCreationForm()
    
    Text(text = "Text text. tHen text button")

}

/* The Composable for
the form fields to collect sign up data */
@Composable
fun StoreCreationForm(){
    Column() {

    }

}