package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.ui.data.TransactionsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AnalyticsViewModel(private val transactionsRepository: TransactionsRepository,
                                userId: Int) : ViewModel() {

    val analyticsUiState: StateFlow<AnalyticsUiState> =

        transactionsRepository.getBestSellingProducts(userId).map { AnalyticsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = AnalyticsUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class AnalyticsUiState(val dataList: List<BestSellingProduct> = listOf())

data class BestSellingProduct(
    val name: String,
    val quantity: Int
)
