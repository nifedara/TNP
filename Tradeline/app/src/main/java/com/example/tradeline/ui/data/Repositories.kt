package com.example.tradeline.ui.data

import com.example.tradeline.ui.screens.viewModel.BestSellingProduct
import kotlinx.coroutines.flow.Flow

interface  UsersRepository {
    fun getUserByStoreName(storeName: String): Flow<User?>

    suspend fun insertUser(user: User)

}

interface ProductsRepository {
    fun getTotalProductsByUserId(userId: Int): Flow<Int?>

    fun getAllProductsByUserId(userId: Int): Flow<List<Product>>

    fun getProduct(id: Int): Flow<Product>

    fun getProductByName(name: String): Flow<Product>

    fun getAllProductsNameByUserId(userId: Int): List<String>

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

}

interface TransactionsRepository {
    fun getBestSellingProducts(userId: Int): Flow<List<BestSellingProduct>>

    fun getTotalProfitByUserId(userId: Int): Flow<Double?>

    fun getTotalPriceByUserId(userId: Int): Flow<Double?>

    fun getAllTransactions(userId: Int): Flow<List<Transaction>>

    suspend fun insertTransaction(transaction: Transaction)
}