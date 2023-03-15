package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.RecipeItemBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    val differ = AsyncListDiffer(this, diffCallback)

    private var onItemClickListener: ((Recipe)->Unit)? = null

    fun setOnItemClickListener(listener: (Recipe)-> Unit){
        onItemClickListener = listener
    }

    private var onFavoriteClickListener: ((Recipe)->Unit)? = null

    fun setOnFavoriteClickListener(listener: (Recipe)-> Unit){
        onFavoriteClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = differ.currentList[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(recipe) }
        }
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class RecipeViewHolder(val binding: RecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(recipe: Recipe){
            val time = "${recipe.readyInMinutes} mins"
            val favoriteIcon = if(recipe.isFavorite) {
                ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_baseline_favorite_24, null)
            } else {
                ResourcesCompat.getDrawable(itemView.resources, R.drawable.ic_baseline_favorite_border_white_24, null)
            }
            binding.imgIsFavorite.setOnClickListener {
                onFavoriteClickListener?.let { it(recipe) }
            }
            binding.apply {
                imgIsFavorite.setImageDrawable(favoriteIcon)
                tvRecipeTitle.text = recipe.title
                tvRecipeTime.text = time
                Glide.with(itemView).load(recipe.image).into(imgRecipe)
            }
        }
    }

    companion object{
        private val diffCallback = object : DiffUtil.ItemCallback<Recipe>(){
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }
        }
    }
}