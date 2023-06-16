package com.example.tradeline.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/* The database table for users. Collects the users' info on signup.
* Storename is unique. It is the primary key of this table */

@Entity(tableName = "users")
data class User(
    @PrimaryKey val storeName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    val storeLogo: ByteArray = getDefaultStoreLogo()
)