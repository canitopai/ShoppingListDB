package com.canitopai.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.canitopai.shoppinglist.databinding.ActivityMainBinding
import com.canitopai.shoppinglist.db.Product
import com.canitopai.shoppinglist.db.ProductDb

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  database: ProductDb
    private val adapter = ProductAdapter({
        Toast.makeText(this, "Product: $it", Toast.LENGTH_SHORT).show()
    }, {
        database.productDao().delete(it)
        refreshProducts()
    })

    private fun refreshProducts() {
        val products = database.productDao().findProducts()
        adapter.submitList(products)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        binding.rvProduct.adapter = adapter

        binding.btnAdd.setOnClickListener{
            addUser()
        }


        database = Room.databaseBuilder(
            applicationContext,
            ProductDb::class.java,
            "products-db")
            .allowMainThreadQueries()
            .build()


        refreshProducts()
    }

    private fun addUser() {
        val name: String = binding.tbName.text.toString()
        val desc: String = binding.tbDesc.text.toString()
        val price: String = binding.tbPrice.text.toString()



        val addProduct = Product(name,desc, price)

        database.productDao().add(addProduct)

        binding.tbName.setText("")
        binding.tbDesc.setText("")
        binding.tbPrice.setText("")



        refreshProducts()
    }

}