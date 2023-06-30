package com.example.tradeline.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val storeName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
)

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val description: String,
    val costPrice: Double,
    val sellingPrice: Double,
)