package com.example.tradeline.data

import kotlinx.coroutines.flow.Flow

interface  UsersRepository {
    fun getUserByStoreName(storeName: String): Flow<User?>

    suspend fun insertUser(user: User)

}

interface ProductsRepository {
    fun getAllProductsByUserId(userId: Int): Flow<List<Product>>

    fun getProduct(id: Int): Flow<Product>

    fun getProductByName(name: String): Flow<Product>

    fun getAllProductsNameByUserId(userId: Int): List<String>

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

}

interface TransactionsRepository {
    fun getAllTransactions(userId: Int): Flow<List<Transaction>>

    suspend fun insertTransaction(transaction: Transaction)
}