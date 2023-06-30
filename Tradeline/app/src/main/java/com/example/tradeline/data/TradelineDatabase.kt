package com.example.tradeline.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class TradelineDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    //abstract fun transactionDao(): TransactionDao
    //abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var Instance: TradelineDatabase? = null

        fun getDatabase(context: Context): TradelineDatabase {

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TradelineDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}