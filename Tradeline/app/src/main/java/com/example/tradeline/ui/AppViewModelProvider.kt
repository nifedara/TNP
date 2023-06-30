package com.example.tradeline.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tradeline.TradelineApplication
import com.example.tradeline.ui.screens.LoginScreenViewModel
import com.example.tradeline.ui.screens.StoreCreationScreenViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for StoreCreationScreenViewModel
        initializer {
            StoreCreationScreenViewModel(tradelineApplication().container.usersRepository)
        }

        // Initializer for LoginScreenViewModel
        initializer {
            LoginScreenViewModel(tradelineApplication().container.usersRepository)
        }


    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [TradelineApplication].
 */
fun CreationExtras.tradelineApplication(): TradelineApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TradelineApplication)
