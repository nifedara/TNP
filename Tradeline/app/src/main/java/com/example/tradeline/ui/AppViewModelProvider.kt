@file:Suppress("UNCHECKED_CAST")

package com.example.tradeline.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tradeline.TradelineApplication
import com.example.tradeline.ui.screens.viewModel.*

object AppViewModelProvider {
    private lateinit var application: TradelineApplication

    fun initialize(application: TradelineApplication) {
        this.application = application
    }

    fun createFactory(userId: Int? = null, itemId: Int? = null, storeName: String? = null): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val usersRepository = application.container.usersRepository
                val productsRepository = application.container.productsRepository
                val transactionsRepository = application.container.transactionsRepository

                if (modelClass.isAssignableFrom(StoreCreationScreenViewModel::class.java)) {
                    return StoreCreationScreenViewModel(usersRepository) as T
                }

                if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
                    return application.createLoginScreenViewModel() as T
                }

                if (modelClass.isAssignableFrom(InventoryLandingViewModel::class.java)) {
                    return InventoryLandingViewModel(productsRepository, userId ?: 0) as T
                }

                if (modelClass.isAssignableFrom(InventoryAddProductScreenViewModel::class.java)) {
                    return InventoryAddProductScreenViewModel(productsRepository) as T
                }

                if (modelClass.isAssignableFrom(ProductDetailsScreenViewModel::class.java)) {
                    return ProductDetailsScreenViewModel(productsRepository, itemId ?: 0) as T
                }

                if (modelClass.isAssignableFrom(AccountScreenViewModel::class.java)) {
                    return AccountScreenViewModel(usersRepository, storeName ?: "") as T
                }

                if (modelClass.isAssignableFrom(RestockViewModel::class.java)) {
                    return RestockViewModel(productsRepository, userId ?: 0) as T
                }

                if (modelClass.isAssignableFrom(SalesViewModel::class.java)) {
                    return SalesViewModel(transactionsRepository, userId ?: 0) as T
                }

                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }

    //private fun TradelineApplication.createLoginScreenViewModel(userId: Int): LoginScreenViewModel {
    private fun TradelineApplication.createLoginScreenViewModel(): LoginScreenViewModel {
        //return LoginScreenViewModel(this.container.usersRepository, userId)
        return LoginScreenViewModel(this.container.usersRepository)
    }
}