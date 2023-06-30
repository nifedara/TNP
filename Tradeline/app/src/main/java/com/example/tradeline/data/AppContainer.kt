package com.example.tradeline.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val usersRepository: UsersRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    //Implementation for UsersRepository
    override val usersRepository: UsersRepository by lazy {
        OfflineUserRepositories(TradelineDatabase.getDatabase(context).userDao())
    }
}