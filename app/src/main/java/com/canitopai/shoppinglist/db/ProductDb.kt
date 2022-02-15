package com.canitopai.shoppinglist.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class ProductDb: RoomDatabase() {
    abstract fun productDao(): ProductDAO
}
