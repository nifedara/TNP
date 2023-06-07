package com.example.tradeline.data

import androidx.room.Dao
import androidx.room.Insert

/**
 * Database access object to access the Tradeline database
 */
@Dao
interface TransactionDao {

    //insert a new transaction on sales
    @Insert
    suspend fun insertTransaction(transaction: Transaction)

}