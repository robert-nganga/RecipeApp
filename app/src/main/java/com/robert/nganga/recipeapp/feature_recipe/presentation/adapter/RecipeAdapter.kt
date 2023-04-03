package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robert.nganga.recipeapp.databinding.RecipeItemBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    val differ = AsyncListDiffer(this, diffCallback)

    private var onItemClickListener: ((Recipe)->Unit)? = null

    fun setOnItemClickListener(listener: (Recipe)-> Unit){
        onItemClickListener = listener
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
            binding.apply {
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