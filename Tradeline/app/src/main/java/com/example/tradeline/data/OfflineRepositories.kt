package com.example.tradeline.data

import kotlinx.coroutines.flow.Flow


class OfflineUserRepositories(private val userDao: UserDao) : UsersRepository {

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun getUserByStoreName(storeName: String): Flow<User?> = userDao.getUser(storeName)

}

class OfflineProductsRepositories(private val productDao: ProductDao) : ProductsRepository {

    override suspend fun insertProduct(product: Product) = productDao.insert(product)

    override suspend fun updateProduct(product: Product)  = productDao.update(product)

    override suspend fun deleteProduct(product: Product) = productDao.delete(product)

    override fun getProduct(id: Int): Flow<Product> = productDao.getProduct(id)

    override fun getAllProductsByUserId(userId: Int): Flow<List<Product>> = productDao.getAllProductsByUserId(userId)

}
