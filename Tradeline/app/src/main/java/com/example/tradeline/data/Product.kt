package com.example.tradeline.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/* The database table for products. stores the products in the inventory
* productId is unique. It is the primary key of this table */

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val productId: Long,
    val productName: String,
    val quantity: Int,
    val description: String,
    val costPrice: Double,
    val sellingPrice: Double,
    val category: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val productImage: ByteArray

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (!productImage.contentEquals(other.productImage)) return false

        return true
    }

    override fun hashCode(): Int {
        return productImage.contentHashCode()
    }
}