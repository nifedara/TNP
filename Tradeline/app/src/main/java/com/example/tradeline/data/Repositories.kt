package com.example.tradeline.data

import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUserByStoreName(storeName: String): Flow<User?>

    suspend fun insertUser(user: User)

}

interface ProductsRepository {
    fun getAllProductsByUserId(userId: Int): Flow<List<Product>>

    fun getProduct(id: Int): Flow<Product>

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

}