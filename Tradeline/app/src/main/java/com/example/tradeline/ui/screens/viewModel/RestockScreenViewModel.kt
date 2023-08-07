package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.ui.data.Product
import com.example.tradeline.ui.data.ProductsRepository
import kotlinx.coroutines.flow.*

class RestockViewModel(
    private val productsRepository: ProductsRepository, userId: Int
) : ViewModel() {

    val restockUiState: StateFlow<RestockUiState> =
        productsRepository.getAllProductsByUserId(userId).map { products ->
            RestockUiState(products.map { it.name })
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = RestockUiState(emptyList())
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val currentProduct = MutableStateFlow<Product?>(null)

    suspend fun getProduct(name: String) {
        val product = productsRepository.getProductByName(name).first()
        setCurrentProduct(product)
    }

    private fun setCurrentProduct(product: Product){
        currentProduct.value = product
    }

    suspend fun updateProduct(updatedQuantity: Int, updatedDescription: String, updatedCost: Double, updatedSelling: Double) {
        val currentProduct = currentProduct.value

        val updatedProduct = currentProduct?.copy(quantity = updatedQuantity, description = updatedDescription, costPrice = updatedCost, sellingPrice = updatedSelling)

        if (updatedProduct != null) {
            productsRepository.updateProduct(updatedProduct)
        }
    }

}


//Ui State for InventoryHomeScreen
data class RestockUiState(val productsName: List<String>)
