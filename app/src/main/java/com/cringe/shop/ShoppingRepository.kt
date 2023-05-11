package com.cringe.shop

import com.cringe.shop.database.AppDatabase
import com.cringe.shop.database.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ShoppingRepository(private val database: AppDatabase) {
    suspend fun addItem(shoppingItem: ShoppingItem){
        withContext(Dispatchers.IO){
            database.shoppingDao().insertItems(shoppingItem)
        }
    }

    val shoppingItems: Flow<List<ShoppingItem>> = database.shoppingDao().getAllItems()

    suspend fun getItemFromId(id: Int): ShoppingItem{
        return database.shoppingDao().getItem(id)
    }
}