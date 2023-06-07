package com.example.tradeline.data

import androidx.room.Dao
import androidx.room.Insert

/**
 * Database access object to access the Tradeline database
 */
@Dao
interface UserDao {

    //insert a new user on signup
    @Insert
    suspend fun insertUser(user: User)

}