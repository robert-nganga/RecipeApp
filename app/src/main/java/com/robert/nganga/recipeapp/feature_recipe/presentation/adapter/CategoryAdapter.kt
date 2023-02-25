package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.robert.nganga.recipeapp.R
import com.robert.nganga.recipeapp.databinding.CategoryItemBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Category

class CategoryAdapter(private val categories: List<Category>):
        RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0

    // get selected category

    fun getSelectedCategory(): Category {
        return categories[selectedPosition]
    }


    private var onItemClickListener: ((Category)->Unit)? = null

    fun setOnItemClickListener(listener: (Category)-> Unit){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(category: Category) {
            binding.apply {
                tvCategory.text = category.title
                imgCategory.setImageResource(category.image)

                // Change the background color of the selected category
                if (selectedPosition == adapterPosition) {
                    cardCategory.setCardBackgroundColor(AppCompatResources.getColorStateList(root.context, R.color.green_200))
                }else{
                    cardCategory.setCardBackgroundColor(AppCompatResources.getColorStateList(root.context, R.color.green_100))
                }

                itemView.setOnClickListener {
                selectedPosition = adapterPosition
                notifyDataSetChanged()
                onItemClickListener?.let { it(category) }
                }
            }
        }

    }
}