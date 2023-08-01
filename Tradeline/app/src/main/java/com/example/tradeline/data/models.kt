package com.example.tradeline.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val storeName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
)

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var name: String,
    val quantity: Int,
    val description: String,
    val costPrice: Double,
    var sellingPrice: Double,
    val userId: Int? = null // Foreign key
)

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val date: String,
    val product: String,
    val quantity: Int,
    val price: Int,
    val userId: Int? = null
)