package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robert.nganga.recipeapp.core.util.Constants.BASE_IMAGE_URL
import com.robert.nganga.recipeapp.databinding.IngredientListItemBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.ExtendedIngredient

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val binding = IngredientListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredients = differ.currentList[position]
        holder.setData(ingredients)
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class IngredientsViewHolder(private val binding: IngredientListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun setData(ingredients: ExtendedIngredient){
                val imageUrl = "$BASE_IMAGE_URL${ingredients.image}"
                binding.apply {
                    tvIngredient.text = ingredients.original
                    Glide.with(itemView)
                        .load(imageUrl)
                        .into(imgIngredient)
                }
            }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExtendedIngredient>(){
            override fun areItemsTheSame(
                oldItem: ExtendedIngredient,
                newItem: ExtendedIngredient
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ExtendedIngredient,
                newItem: ExtendedIngredient
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}