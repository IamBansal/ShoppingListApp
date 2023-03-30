package com.example.shoppinglistapp.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.databinding.ShoppingItemBinding
import com.example.shoppinglistapp.ui.ShoppingViewModel

class ShoppingItemAdapter(var items: List<ShoppingItem>, private var viewModel: ShoppingViewModel) :
    RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(private val binding: ShoppingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShoppingItem) {

            binding.tvName.text = item.name
            binding.tvAmount.text = item.amount.toString()

            binding.ivDelete.setOnClickListener {
                viewModel.delete(item)
            }
            binding.ivAdd.setOnClickListener {
                item.amount++
                viewModel.upsert(item)
            }
            binding.ivRemove.setOnClickListener {
                if(item.amount > 0){
                    item.amount--
                    viewModel.upsert(item)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]
        holder.bind(currentShoppingItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}