package com.canitopai.shoppinglist.db

import androidx.room.*

@Dao
interface ProductDAO {
    @Query("SELECT * FROM Product ORDER BY Product.name")
    fun findProducts(): List<Product>
    @Query("SELECT * FROM Product WHERE Product.id = :productId LIMIT 1")
    fun findProductById(productId: Int):Product
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(product: Product)
    @Delete
    fun delete(product: Product)
}