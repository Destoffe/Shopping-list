package com.cringe.shop

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cringe.shop.database.ShoppingItem
import com.cringe.shop.ui.theme.ShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<ShoppingViewModel>()
            val shoppingItems by viewModel.shoppingItems.collectAsState(initial = listOf())
            ShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(shoppingItems = shoppingItems, onAddItemClick = {shoppingItem ->  viewModel.addShoppingItem(shoppingItem)})
                }
            }
        }
    }
}

@Composable
fun MainScreen(shoppingItems: List<ShoppingItem>,onAddItemClick: (ShoppingItem) -> Unit){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Shopping List")})
        },
        content = {
            Column {
                Input(onAddItemClick)
                ShoppingList(shoppingItems)
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
            placeholder = { Text(text = "Product name")}
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
fun ShoppingList(shoppingItems: List<ShoppingItem>){
    LazyColumn{
        items(
            items = shoppingItems
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {},
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShopTheme {
        MainScreen(listOf(),{})
    }
}