package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments.IngredientsFragment
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments.PreparationFragment
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments.SummaryFragment

private const val NUM_TABS = 3

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
        FragmentStateAdapter(fragmentManager, lifecycle) {

    private var recipe: Recipe? = null

    fun setRecipe(recipe: Recipe){
        this.recipe = recipe
    }
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                val ingredientsFragment = IngredientsFragment()
                recipe?.let {
                    ingredientsFragment.arguments = Bundle().apply {
                        putSerializable("recipe", it)
                    }
                }
                ingredientsFragment
            }
            1 -> {
                val preparationFragment = PreparationFragment()
                recipe?.let {
                    preparationFragment.arguments = Bundle().apply {
                        putSerializable("recipe", it)
                    }
                }
                preparationFragment
            }
            else -> {
                val summaryFragment = SummaryFragment()
                recipe?.let {
                    summaryFragment.arguments = Bundle().apply {
                        putSerializable("recipe", it)
                    }
                }
                summaryFragment
            }
        }
    }
}