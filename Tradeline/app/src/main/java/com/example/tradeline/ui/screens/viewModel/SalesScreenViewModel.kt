package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.data.Transaction
import com.example.tradeline.data.TransactionsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SalesViewModel(
    private val transactionsRepository: TransactionsRepository,
    userId: Int) : ViewModel() {

    val salesUiState: StateFlow<SalesUiState> =

        transactionsRepository.getAllTransactions(userId).map { SalesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = SalesUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

//Ui State for InventoryHomeScreen
data class SalesUiState(val itemList: List<Transaction> = listOf())