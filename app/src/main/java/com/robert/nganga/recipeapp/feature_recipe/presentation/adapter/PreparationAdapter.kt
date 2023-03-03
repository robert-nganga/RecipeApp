package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.robert.nganga.recipeapp.databinding.PreparationListItemBinding
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Step

class PreparationAdapter: RecyclerView.Adapter<PreparationAdapter.PreparationViewHolder>() {

    val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreparationViewHolder {
        val binding = PreparationListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return PreparationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreparationViewHolder, position: Int) {
        val step = differ.currentList[position]
        holder.bind(step)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class PreparationViewHolder(private val binding: PreparationListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(step: Step) {
                val stepNumber = "Step ${step.number}"
                binding.apply {
                    tvStepNumber.text = stepNumber
                    tvStep.text = step.step
                }
            }
        }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Step>() {
            override fun areItemsTheSame(oldItem: Step, newItem: Step): Boolean {
                return oldItem.number == newItem.number
            }

            override fun areContentsTheSame(oldItem: Step, newItem: Step): Boolean {
                return oldItem == newItem
            }
        }
    }
}