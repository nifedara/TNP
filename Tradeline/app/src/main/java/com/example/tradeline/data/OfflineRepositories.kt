package com.example.tradeline.data

import kotlinx.coroutines.flow.Flow


class OfflineUserRepositories(private val userDao: UserDao) : UsersRepository {

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun getUserByStoreName(storeName: String): Flow<User?> = userDao.getUser(storeName)


}
