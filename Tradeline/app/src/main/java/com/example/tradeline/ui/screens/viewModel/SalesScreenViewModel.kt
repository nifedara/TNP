package com.example.tradeline.ui.screens.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.ui.data.Product
import com.example.tradeline.ui.data.ProductsRepository
import com.example.tradeline.ui.data.Transaction
import com.example.tradeline.ui.data.TransactionsRepository
import kotlinx.coroutines.flow.*

class SalesViewModel(
    private val transactionsRepository: TransactionsRepository,
    private val productsRepository: ProductsRepository,
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

    val  saleUiState: StateFlow<SaleDetailsUiState> =
        productsRepository.getAllProductsByUserId(userId).map { products ->
            SaleDetailsUiState(products.map { it.name })
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = SaleDetailsUiState(emptyList())
        )

    val currentProduct = MutableStateFlow<Product?>(null)

    suspend fun getProduct(name: String) {
        val product = productsRepository.getProductByName(name).first()
        setCurrentProduct(product)
    }

    private fun setCurrentProduct(product: Product){
        currentProduct.value = product
    }


    suspend fun updateProductInsertTransaction(date: String, product: String, quantity: Int, price: Double, userId: Int) {
        val currentProduct = currentProduct.value

        val currentQuantity = currentProduct!!.quantity
        val updatedQuantity = currentQuantity - quantity
        val updatedProduct = currentProduct.copy(quantity = updatedQuantity)

        val transaction =  Transaction(date = date, product = product, quantity = quantity, price = price, userId = userId)

        if (updatedQuantity >= 0) {
            productsRepository.updateProduct(updatedProduct)
            transactionsRepository.insertTransaction(transaction)
            handleSuccessfulInsert()
        } else{
            handleInvalidInsert()
        }
    }

    private val recordSalesState = mutableStateOf(RecordSalesState())

    private fun handleSuccessfulInsert() {
        recordSalesState.value = RecordSalesState(successfulInsert = true)
    }

    private fun handleInvalidInsert() {
        recordSalesState.value = RecordSalesState(invalidInsert = true)
    }
}

data class RecordSalesState(
    val successfulInsert: Boolean = false,
    val invalidInsert: Boolean = false
)

//Ui State for InventoryHomeScreen
data class SalesUiState( val itemList: List<Transaction> = listOf())

data class SaleDetailsUiState(val productsName: List<String>)