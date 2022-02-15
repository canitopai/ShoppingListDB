package com.canitopai.shoppinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.canitopai.shoppinglist.databinding.ItemProductBinding
import com.canitopai.shoppinglist.db.Product
import kotlin.math.absoluteValue

class ProductAdapter(
    private val onItemClick: (Product) -> Unit,
    private val deleteItemClick: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ViewHolder>(UserDiffUtils()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)

        holder.binding.tvName.text = product.name
        holder.binding.tvPrice.text = product.price.toString()
        holder.binding.tvDesc.text = product.desc
        holder.binding.tvCurrency.text = "€"
        holder.binding.root.setOnClickListener{
            onItemClick(product)
        }

        holder.binding.btnRemove.setOnClickListener{
            deleteItemClick(product)
        }

    }


    inner class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

}


class UserDiffUtils: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id
}