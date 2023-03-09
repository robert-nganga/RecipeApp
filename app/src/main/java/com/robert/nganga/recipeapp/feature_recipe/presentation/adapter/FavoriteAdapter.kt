package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robert.nganga.recipeapp.databinding.FavoriteListItemBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Favorite

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = differ.currentList[position]
        holder.setData(favorite)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class FavoriteViewHolder(private val binding: FavoriteListItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun setData(favorite: Favorite){
            val time = "${favorite.readyInMinutes} mins"
            binding.apply {
                tvFavoriteTitle.text = favorite.title
                tvFavoriteTime.text = time
                tvFavoriteLikes.text = favorite.aggregateLikes.toString()
                tvFavoriteServings.text = favorite.servings.toString()
                Glide.with(itemView)
                    .load(favorite.image)
                    .into(imgFavorite)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Favorite>(){
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }
        }
    }
}