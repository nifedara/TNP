package com.example.tradeline.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)
abstract class TradelineDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    //abstract fun transactionDao(): TransactionDao
    //abstract fun productDao(): ProductDao


    companion object {
        @Volatile
        private var Instance: TradelineDatabase? = null

        fun getDatabase(context: Context): TradelineDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TradelineDatabase::class.java, "user_database")

                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
