package com.example.tradeline.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [User::class, Product::class], version = 2, exportSchema = false)
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
                    .addMigrations(Migration1To2)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

object Migration1To2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "quantity INTEGER NOT NULL, " +
                    "description TEXT NOT NULL, " +
                    "costPrice REAL NOT NULL, " +
                    "sellingPrice REAL NOT NULL, " +
//                    "userId INTEGER NOT NULL, " +
//                    "FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE)"
                    "user_id INTEGER NOT NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE)"
        )
    }
}

