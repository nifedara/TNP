package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.data.Product
import com.example.tradeline.data.ProductsRepository
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

}

//UI state for ProductDetailsScreen
data class ProductDetailsUiState(
    val outOfStock: Boolean = true,
    val itemDetails: ProductDetails = ProductDetails()
)

data class ProductDetails(
    val id: Int = 0,
    var name: String = "",
    val sellingPrice: String = "",
    val costPrice: String = "",
    val quantity: String = "",
    val description: String = "",
) {
    fun toProduct(): Product = Product(
        id = id,
        name = name,
        sellingPrice = sellingPrice.toDoubleOrNull() ?: 0.0,
        costPrice = costPrice.toDoubleOrNull() ?: 0.0,
        quantity = quantity.toIntOrNull() ?: 0,
        description = description,
    )
}

//Extension function to convert [Product] to [ProductDetails]
fun Product.toProductDetails(): ProductDetails = ProductDetails(
    id = id!!,
    name = name,
    sellingPrice = sellingPrice.toString(),
    quantity = quantity.toString(),
    description = description
)