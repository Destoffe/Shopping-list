package com.cringe.shop

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cringe.shop.database.ShoppingItem
import com.cringe.shop.database.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingViewModel(application: Application): AndroidViewModel(application) {
    private val shoppingRepository = ShoppingRepository(getDatabase(application))
    val item = MutableStateFlow<ShoppingItem?>(null)


    fun setId(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val shoppingItem = shoppingRepository.getItemFromId(id)
                item.value = shoppingItem
            }

        }
    }

    val shoppingItems = shoppingRepository.shoppingItems

    fun addShoppingItem(shoppingItem: ShoppingItem){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                shoppingRepository.addItem(shoppingItem)
            }
        }
    }
}