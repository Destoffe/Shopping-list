package com.cringe.shop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShoppingItem::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shoppingDao(): ShoppingDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
        synchronized(AppDatabase::class.java){
            if(!::INSTANCE.isInitialized){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database-name"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    return INSTANCE
}