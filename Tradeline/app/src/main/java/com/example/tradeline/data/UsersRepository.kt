package com.example.tradeline.data

interface UsersRepository {

    suspend fun insertUser(user: User)
}