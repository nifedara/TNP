package com.example.tradeline.data

import androidx.room.Entity
import androidx.room.ForeignKey
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

@Entity(tableName = "products",
        foreignKeys = [ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE)])
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val quantity: Int,
    val description: String,
    val costPrice: Double,
    val sellingPrice: Double,
    val userId: Int = 0 // Foreign key
)