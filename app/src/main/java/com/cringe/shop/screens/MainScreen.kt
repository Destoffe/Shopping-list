package com.cringe.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cringe.shop.database.ShoppingItem

@Composable
fun MainScreen(
    shoppingViewModel: ShoppingViewModel,
    navHostController: NavHostController

){
    val shoppingItems by shoppingViewModel.shoppingItems.collectAsState(initial = listOf())
    MainScreen(
        shoppingItems = shoppingItems,
        onAddItemClick =  {shoppingItem -> shoppingViewModel.addShoppingItem(shoppingItem)},
        onNavigateToItem = {id -> navHostController.navigate("item/$id")}
    )
}


@Composable
fun MainScreen(shoppingItems: List<ShoppingItem>, onAddItemClick: (ShoppingItem) -> Unit, onNavigateToItem: (String) -> Unit,){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Shopping List") })
        },
        content = {
            Column {
                Input(onAddItemClick)
                ShoppingList(shoppingItems, onItemClick = onNavigateToItem)
            }

        }
    )
}

@Composable
fun Input(onAddItemClick: (ShoppingItem) -> Unit){
    var itemName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Add item to the shopping list")
        TextField(
            value = itemName,
            onValueChange = { itemName = it},
            placeholder = { Text(text = "Product name") }
        )
        TextField(
            value = amount.toString(),
            onValueChange = { amount = if(it.isBlank()) 0 else it.toInt()},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Button(onClick = { onAddItemClick(ShoppingItem(itemName,amount))}) {
            Text(text = "Add product")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShoppingList(
    shoppingItems: List<ShoppingItem>,
    onItemClick: (String) -> Unit,
){
    LazyColumn{
        items(
            items = shoppingItems
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {onItemClick(it.id.toString())},
                elevation = 3.dp
            ) {
                Text(
                    text = it.name,
                    modifier = Modifier.padding(12.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}