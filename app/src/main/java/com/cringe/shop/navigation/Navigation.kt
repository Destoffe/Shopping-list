package com.cringe.shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.cringe.shop.MainScreen
import com.cringe.shop.ShoppingViewModel
import com.cringe.shop.database.ShoppingItem
import com.cringe.shop.screens.ItemScreen

@Composable
fun NavHost(
    navHostController: NavHostController,
    shoppingViewModel: ShoppingViewModel,

    ){
    androidx.navigation.compose.NavHost(navController = navHostController, startDestination = "main"){
        composable("main"){
            MainScreen(
                shoppingViewModel = shoppingViewModel,
                navHostController = navHostController
            )

        }
        composable(route = "item/{itemId}"){backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            itemId?.let {
                ItemScreen(itemId = itemId, shoppingViewModel = shoppingViewModel)
            }

        }
    }
}
