package com.example.tradeline.data

import androidx.room.ColumnInfo
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
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val storeLogo: ByteArray

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (!storeLogo.contentEquals(other.storeLogo)) return false

        return true
    }

    override fun hashCode(): Int {
        return storeLogo.contentHashCode()
    }
}
