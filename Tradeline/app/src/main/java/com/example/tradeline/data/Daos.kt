package com.example.tradeline.data

import androidx.room.*
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

}