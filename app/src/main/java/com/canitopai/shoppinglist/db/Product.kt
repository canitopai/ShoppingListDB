package com.canitopai.shoppinglist.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class Product(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val desc: String,
    @ColumnInfo(name = "price")
    val price: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
