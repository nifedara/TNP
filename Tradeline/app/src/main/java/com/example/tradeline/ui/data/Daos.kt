package com.example.tradeline.ui.data

import androidx.room.*
import com.example.tradeline.ui.screens.viewModel.BestSellingProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE storeName = :storeName")
    fun getUser(storeName: String): Flow<User?>

}

@Dao
interface ProductDao {
    @Query("SELECT COUNT(name) FROM products WHERE userId = :userId ORDER BY name ASC")
    fun getTotalProductsByUserId(userId: Int): Flow<Int?>

    @Query("SELECT * FROM products WHERE userId = :userId ORDER BY name ASC")
    fun getAllProductsByUserId(userId: Int): Flow<List<Product>>

    @Query("SELECT * from products WHERE id = :id")
    fun getProduct(id: Int): Flow<Product>

    @Query("SELECT * from products WHERE name = :name")
    fun getProductByName(name: String): Flow<Product>

    @Query("SELECT name FROM products WHERE userID = :userId ORDER BY name ASC")
    fun getAllProductsNameByUserId(userId: Int): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)
}

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE userId = :userId ORDER BY product ASC")
    fun getAllTransactions(userId: Int): Flow<List<Transaction>>

    @Query("SELECT SUM(price) FROM transactions WHERE userId = :userId")
    fun getTotalPriceByUserId(userId: Int): Flow<Double?>

    @Query("SELECT SUM(t.price - (p.costPrice * t.quantity)) FROM transactions t INNER JOIN products p ON t.product = p.name WHERE t.userId = :userId")
    fun getTotalProfitByUserId(userId: Int): Flow<Double?>

    @Query("SELECT product AS name, SUM(quantity) AS quantity FROM transactions WHERE userId = :userId GROUP BY product ORDER BY SUM(quantity) DESC LIMIT 5")
    fun getBestSellingProduct(userId: Int): Flow<List<BestSellingProduct>>
}

