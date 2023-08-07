package com.example.tradeline.ui.data

import com.example.tradeline.ui.screens.viewModel.BestSellingProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class OfflineUserRepositories(private val userDao: UserDao) : UsersRepository {

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override fun getUserByStoreName(storeName: String): Flow<User?> = userDao.getUser(storeName)

}

class OfflineProductsRepositories(private val productDao: ProductDao) : ProductsRepository {

    override fun getTotalProductsByUserId(userId: Int): Flow<Int?> {
        return productDao.getTotalProductsByUserId(userId)
            .map  { totalProducts -> totalProducts ?: 0 }
    }

    override suspend fun insertProduct(product: Product) = productDao.insert(product)

    override suspend fun updateProduct(product: Product)  = productDao.update(product)

    override suspend fun deleteProduct(product: Product) = productDao.delete(product)

    override fun getProduct(id: Int): Flow<Product> = productDao.getProduct(id)

    override fun getProductByName(name: String): Flow<Product> = productDao.getProductByName(name)

    override fun getAllProductsByUserId(userId: Int): Flow<List<Product>> = productDao.getAllProductsByUserId(userId)

    override fun getAllProductsNameByUserId(userId: Int): List<String> = productDao.getAllProductsNameByUserId(userId)

}

class OfflineTransactionsRepositories(private val transactionDao: TransactionDao) :
    TransactionsRepository {
    override fun getBestSellingProducts(userId: Int): Flow<List<BestSellingProduct>> = transactionDao.getBestSellingProduct(userId)

    override fun getTotalProfitByUserId(userId: Int): Flow<Double?> {
        return transactionDao.getTotalProfitByUserId(userId)
            .map { totalProfit -> totalProfit ?: 0.0 }
    }

    override fun getTotalPriceByUserId(userId: Int): Flow<Double?> {
        return transactionDao.getTotalPriceByUserId(userId)
            .map { totalSales -> totalSales ?: 0.0 }
    }

    override suspend fun insertTransaction(transaction: Transaction) = transactionDao.insert(transaction)

    override fun getAllTransactions(userId: Int): Flow<List<Transaction>> = transactionDao.getAllTransactions(userId)

}
