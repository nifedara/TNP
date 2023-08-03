package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.data.Product
import com.example.tradeline.data.ProductsRepository
import com.example.tradeline.data.Transaction
import com.example.tradeline.data.TransactionsRepository
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

    suspend fun updateProduct(updatedQuantity: Int) {
        val currentProduct = currentProduct.value

        val updatedProduct = currentProduct?.copy(quantity = updatedQuantity)

        if (updatedProduct != null) {
            productsRepository.updateProduct(updatedProduct)
        }
    }

    suspend fun insertTransaction(date: String, product: String, quantity: Int, price: Double, userId: Int){
       val transaction =  Transaction(date = date, product = product, quantity = quantity, price = price, userId = userId)
       transactionsRepository.insertTransaction(transaction)
    }

}

//Ui State for InventoryHomeScreen
data class SalesUiState( val itemList: List<Transaction> = listOf())

data class SaleDetailsUiState(val productsName: List<String>)