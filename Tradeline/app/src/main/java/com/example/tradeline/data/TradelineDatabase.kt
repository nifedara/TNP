package com.example.tradeline.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class, Product::class], version = 7, exportSchema = false)
abstract class TradelineDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    //abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var Instance: TradelineDatabase? = null

        fun getDatabase(context: Context): TradelineDatabase {

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TradelineDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    //.addMigrations(Migration1To2)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

