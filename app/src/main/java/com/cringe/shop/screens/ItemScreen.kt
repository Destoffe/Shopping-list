package com.cringe.shop.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cringe.shop.ShoppingViewModel

@Composable
fun ItemScreen(
    shoppingViewModel: ShoppingViewModel,
    itemId: String,
){
    shoppingViewModel.setId(itemId.toInt())
    val item by shoppingViewModel.item.collectAsState(initial = null)

    Row() {
        Text(text = item?.name + ": " + item?.amount)
    }
}