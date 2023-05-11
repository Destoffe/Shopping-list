package com.cringe.shop.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.cringe.shop.ShoppingViewModel

@Composable
fun ItemScreen(
    shoppingViewModel: ShoppingViewModel,
    itemId: String,
    navController: NavController,
){
    shoppingViewModel.setId(itemId.toInt())
    val item by shoppingViewModel.item.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Item view") },
                navigationIcon =  if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }
            )
        },
        content = {
            Row() {
                Text(text = item?.name + ": " + item?.amount)
            }
        }
    )
}