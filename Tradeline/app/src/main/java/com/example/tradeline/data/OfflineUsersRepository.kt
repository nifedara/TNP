package com.example.tradeline.data


class OfflineUsersRepository(private val userDao: UserDao) : UsersRepository {

    override suspend fun insertUser(user: User) = userDao.insert(user)
}
