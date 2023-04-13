package com.cringe.shop.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingItem(
    val name: String,
    val amount: Int,
    @PrimaryKey(autoGenerate = true)val id: Int = 0
)
