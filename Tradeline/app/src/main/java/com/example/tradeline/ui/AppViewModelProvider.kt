package com.example.tradeline.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tradeline.TradelineApplication
import com.example.tradeline.ui.screens.InventoryAddProductScreenViewModel
import com.example.tradeline.ui.screens.InventoryLandingViewModel
import com.example.tradeline.ui.screens.LoginScreenViewModel
import com.example.tradeline.ui.screens.StoreCreationScreenViewModel

object AppViewModelProvider {
    private lateinit var application: TradelineApplication

    fun initialize(application: TradelineApplication) {
        this.application = application
    }

    fun createFactory(userId: Int = 0): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val usersRepository = application.container.usersRepository
                val productsRepository = application.container.productsRepository

                if (modelClass.isAssignableFrom(StoreCreationScreenViewModel::class.java)) {
                    return StoreCreationScreenViewModel(usersRepository) as T
                }

                if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
                    return application.createLoginScreenViewModel(userId) as T
                }

                if (modelClass.isAssignableFrom(InventoryLandingViewModel::class.java)) {
                    return InventoryLandingViewModel(userId, productsRepository) as T
                }

                if (modelClass.isAssignableFrom(InventoryAddProductScreenViewModel::class.java)) {
                    return InventoryAddProductScreenViewModel(productsRepository) as T
                }

                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }

    private fun TradelineApplication.createLoginScreenViewModel(userId: Int): LoginScreenViewModel {
        return LoginScreenViewModel(this.container.usersRepository, userId)
    }
}







/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
//object AppViewModelProvider {
//    val Factory = viewModelFactory {
//
//        // Initializer for StoreCreationScreenViewModel
//        initializer {
//            StoreCreationScreenViewModel(tradelineApplication().container.usersRepository)
//        }
//
//        // Initializer for LoginScreenViewModel
//        initializer {
//            LoginScreenViewModel(tradelineApplication().container.usersRepository)
//        }
//
//        // Initializer for InventoryLandingViewModel
//        initializer {
//            InventoryLandingViewModel(
//                this.createSavedStateHandle(),
//                tradelineApplication().container.productsRepository)
//        }
//
//    }
//}
//
///**
// * Extension function to queries for [Application] object and returns an instance of
// * [TradelineApplication].
// */
//fun CreationExtras.tradelineApplication(): TradelineApplication =
//    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TradelineApplication)
