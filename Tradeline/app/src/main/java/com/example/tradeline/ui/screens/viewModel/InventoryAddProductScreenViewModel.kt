package com.example.tradeline.ui.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tradeline.data.Product
import com.example.tradeline.data.ProductsRepository
import kotlinx.coroutines.launch

class InventoryAddProductScreenViewModel(private val productsRepository: ProductsRepository) : ViewModel() {
    suspend fun createNewProduct(productName: String, productQuantity: String, description: String, costPrice: String, sellingPrice: String, userId: Int) {

        if (productName.isNotBlank() && productQuantity.isNotBlank() && description.isNotBlank() && costPrice.isNotBlank() && sellingPrice.isNotBlank()){

            viewModelScope.launch {

                val product = Product(name = productName, quantity = productQuantity.toIntOrNull() ?: 0, description = description,
                costPrice = costPrice.toDoubleOrNull() ?: 0.0, sellingPrice = sellingPrice.toDoubleOrNull() ?: 0.0, userId = userId)
                productsRepository.insertProduct(product)

            }
        }

    }
}