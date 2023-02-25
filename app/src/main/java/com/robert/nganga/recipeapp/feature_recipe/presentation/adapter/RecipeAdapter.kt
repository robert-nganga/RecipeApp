package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robert.nganga.recipeapp.databinding.RecipeItemBinding
import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.RecipeEntity
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = differ.currentList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class RecipeViewHolder(private val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(recipe: Recipe){
            val time = "${recipe.readyInMinutes} mins"
            binding.apply {
                tvRecipeTitle.text = recipe.title
                tvRecipeTime.text = time
                Glide.with(itemView).load(recipe.image).into(imgRecipe)
            }
        }
    }
}