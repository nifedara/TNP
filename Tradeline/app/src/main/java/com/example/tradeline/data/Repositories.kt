package com.example.tradeline.data

import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUserByStoreName(storeName: String): Flow<User?>

    suspend fun insertUser(user: User)

}