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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)
}