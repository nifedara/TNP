package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.ui.data.ProductsRepository
import com.example.tradeline.ui.data.TransactionsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    transactionsRepository: TransactionsRepository,
    productsRepository: ProductsRepository,
    userId: Int) : ViewModel() {

    val totalSalesUiState: StateFlow<TotalSalesUiState> =

        transactionsRepository.getTotalPriceByUserId(userId).mapNotNull { it?.let { TotalSalesUiState(it) } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TotalSalesUiState()
            )

    val totalProductsUiState: StateFlow<TotalProductsUiState> =

        productsRepository.getTotalProductsByUserId(userId).mapNotNull { it?.let { TotalProductsUiState(it) } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TotalProductsUiState()
            )

    val totalProfitUiState: StateFlow<TotalProfitUiState> =

        transactionsRepository.getTotalProfitByUserId(userId).mapNotNull { it?.let { TotalProfitUiState(it) } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TotalProfitUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
 }

data class TotalSalesUiState(val sales: Double? = null)

data class TotalProductsUiState(val products: Int? = null)

data class TotalProfitUiState(val profit: Double? = null)