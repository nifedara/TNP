package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.ui.data.Product
import com.example.tradeline.ui.data.ProductsRepository
import kotlinx.coroutines.flow.*


class ProductDetailsScreenViewModel(
    private val productsRepository: ProductsRepository,
    itemId: Int) : ViewModel() {

    val detailsUiState: StateFlow<ProductDetailsUiState> =
        productsRepository.getProduct(itemId)
            .filterNotNull()
            .map {
                ProductDetailsUiState(outOfStock = it.quantity <= 0, itemDetails = it.toProductDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ProductDetailsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun deleteProduct() {
        productsRepository.deleteProduct(detailsUiState.value.itemDetails.toProduct())
    }

    suspend fun updateProduct(updatedProductName: String, updatedDescription: String) {
        val currentProduct = detailsUiState.value.itemDetails.toProduct()

        val updatedProduct = currentProduct.copy(name = updatedProductName, description = updatedDescription)

        productsRepository.updateProduct(updatedProduct)
    }
}

//UI state for ProductDetailsScreen
data class ProductDetailsUiState(
    val outOfStock: Boolean = true,
    val isEntryValid: Boolean = false,
    val itemDetails: ProductDetails = ProductDetails()
)

data class ProductDetails(
    val id: Int = 0,
    var name: String = "",
    val sellingPrice: String = "",
    val costPrice: String = "",
    val quantity: String = "",
    var description: String = "",
    val userId: Int = 0,
) {
    fun toProduct(): Product = Product(
        id = id,
        name = name,
        sellingPrice = sellingPrice.toDoubleOrNull() ?: 0.0,
        costPrice = costPrice.toDoubleOrNull() ?: 0.0,
        quantity = quantity.toIntOrNull() ?: 0,
        description = description,
        userId = userId
    )
}

//Extension function to convert [Product] to [ProductDetails]
fun Product.toProductDetails(): ProductDetails = ProductDetails(
    id = id!!,
    name = name,
    sellingPrice = sellingPrice.toString(),
    costPrice = costPrice.toString(),
    quantity = quantity.toString(),
    description = description,
    userId = userId!!
)