package com.mirsalim.managing_checklist.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirsalim.managing_checklist.databinding.ItemFuitBinding
import com.mirsalim.managing_checklist.ui.data.Fruit

class FruitAdapter(val itemChecked:(Fruit)->Unit): ListAdapter<Fruit, FruitAdapter.FruitHolder>(
    object: DiffUtil.ItemCallback<Fruit>(){
        override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
            return oldItem.isChecked == newItem.isChecked && oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
            return oldItem.name == newItem.name && oldItem.isChecked == newItem.isChecked
        }
    }
){
    inner class FruitHolder(private val binding: ItemFuitBinding): RecyclerView.ViewHolder(binding.root){
        fun display(data:Fruit){
            binding.fruitItem.text = data.name
            binding.fruitItem.isChecked = data.isChecked
            binding.fruitItem.setOnClickListener {
                data.isChecked = binding.fruitItem.isChecked
                itemChecked(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitHolder {
        return FruitHolder(
            ItemFuitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FruitHolder, position: Int) {
        holder.display(currentList[position])
    }
}