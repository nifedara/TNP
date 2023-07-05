package com.example.tradeline.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.data.Product
import com.example.tradeline.data.ProductsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class InventoryLandingViewModel(
    private val userId: Int,
    private val productsRepository: ProductsRepository,
    ) : ViewModel() {

    //private val userId: Int = checkNotNull(savedStateHandle[userIdArg])

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

