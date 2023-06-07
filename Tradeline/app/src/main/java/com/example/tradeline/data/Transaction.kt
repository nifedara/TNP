package com.example.tradeline.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/* The database table for transactions. Stores all the sales records(transactions).
* TransactionID is the primary key of this table */

@Entity(tableName = "transactions",
    foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["storeName"], childColumns = ["userStoreName"]),
        ForeignKey(entity = Product::class, parentColumns = ["productId"], childColumns = ["transactionProductId"])
])
data class Transaction (
    @PrimaryKey(autoGenerate = true) val transactionId: Long,
    val productName: String,
    val price: Double,
    val quantity: Int,
    val date: Long,
    val userStoreName: String,  // Foreign key referencing storeName in User entity
    val transactionProductId: Long  // Foreign key referencing productId in Product entity
)