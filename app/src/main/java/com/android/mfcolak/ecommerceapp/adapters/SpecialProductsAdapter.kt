package com.android.mfcolak.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.mfcolak.ecommerceapp.model.Product
import com.android.mfcolak.ecommerceapp.databinding.SpecialRecItemBinding
import com.bumptech.glide.Glide

class SpecialProductsAdapter:RecyclerView.Adapter<SpecialProductsAdapter.SpecialProductViewHolder>() {

    inner class SpecialProductViewHolder(private val binding: SpecialRecItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imgSpecial)
                twSpecialProductName.text = product.name
                twSpecialProductPrice.text = product.price.toString()
            }

        }
    }

    private val diffCallBack = object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialProductViewHolder {
        return SpecialProductViewHolder(
            SpecialRecItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SpecialProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}