package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robert.nganga.recipeapp.databinding.RecipeByIngredientItemBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients

class SearchByIngredientsAdapter: RecyclerView.Adapter<SearchByIngredientsAdapter.SearchByIngredientsViewHolder>() {

    val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    private var onItemClickListener: ((RecipeByIngredients)->Unit)? = null

    fun setOnItemClickListener(listener: (RecipeByIngredients)-> Unit){
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchByIngredientsViewHolder {
        val binding = RecipeByIngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchByIngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchByIngredientsViewHolder, position: Int) {
        val recipeByIngredients = differ.currentList[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(recipeByIngredients) }
        }
        holder.setData(recipeByIngredients)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class SearchByIngredientsViewHolder(private val binding: RecipeByIngredientItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun setData(recipeByIngredients: RecipeByIngredients){
            binding.apply {
                recipeByIngredients.apply {
                    tvRecipeName.text = title
                    Glide.with(itemView).load(image).into(imgSearchByIngredientRecipe)
                }
            }
        }

    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecipeByIngredients>(){
            override fun areItemsTheSame(oldItem: RecipeByIngredients, newItem: RecipeByIngredients): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RecipeByIngredients, newItem: RecipeByIngredients): Boolean {
                return oldItem == newItem
            }

        }
    }
}