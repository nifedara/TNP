package com.example.tradeline.ui.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val usersRepository: UsersRepository

    val productsRepository: ProductsRepository

    val transactionsRepository: TransactionsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    //Implementation for UsersRepository
    override val usersRepository: UsersRepository by lazy {
        OfflineUserRepositories(TradelineDatabase.getDatabase(context).userDao())
    }

    //Implementation for ProductsRepository
    override val productsRepository: ProductsRepository by lazy {
        OfflineProductsRepositories(TradelineDatabase.getDatabase(context).productDao())
    }

    //Implementation for TransactionsRepository
    override val transactionsRepository: TransactionsRepository by lazy {
        OfflineTransactionsRepositories(TradelineDatabase.getDatabase(context).transactionDao())
    }
}