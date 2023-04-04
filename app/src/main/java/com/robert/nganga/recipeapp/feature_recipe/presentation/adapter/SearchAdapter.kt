package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robert.nganga.recipeapp.databinding.RecipeByIngredientItemBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import com.robert.nganga.recipeapp.feature_recipe.domain.model.SearchResult

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var onItemClickListener: ((SearchResult)->Unit)? = null

    fun setOnItemClickListener(listener: (SearchResult)-> Unit){
        onItemClickListener = listener
    }

    val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = RecipeByIngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val searchResult = differ.currentList[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(searchResult) }
        }
        holder.setData(searchResult)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class SearchViewHolder(private val binding: RecipeByIngredientItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun setData(searchResult: SearchResult){
            binding.apply {
                searchResult.apply {
                    tvRecipeName.text = title
                    Glide.with(itemView).load(image).into(imgSearchByIngredientRecipe)
                }
            }
        }

    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchResult>(){
            override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
                return oldItem == newItem
            }

        }
    }
}