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

class PagerAdapter(
    private val recipe: Recipe,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle().apply {
            putSerializable("recipe", recipe)
        }
        return when(position){
            0 -> {
                val ingredientsFragment = IngredientsFragment()
                ingredientsFragment.arguments = bundle
                ingredientsFragment
            }
            1 -> {
                val preparationFragment = PreparationFragment()
                preparationFragment.arguments = bundle
                preparationFragment
            }
            else -> {
                val summaryFragment = SummaryFragment()
                summaryFragment.arguments = bundle
                summaryFragment
            }
        }
    }
}