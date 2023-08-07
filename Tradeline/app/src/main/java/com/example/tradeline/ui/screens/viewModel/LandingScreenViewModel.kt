package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.ui.data.Product
import com.example.tradeline.ui.data.ProductsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class InventoryLandingViewModel(private val productsRepository: ProductsRepository,
                                userId: Int) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =

        productsRepository.getAllProductsByUserId(userId).map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

//Ui State for InventoryHomeScreen
data class HomeUiState(val itemList: List<Product> = listOf())

