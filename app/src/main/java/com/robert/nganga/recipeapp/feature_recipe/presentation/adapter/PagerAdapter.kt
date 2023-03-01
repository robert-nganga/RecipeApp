package com.robert.nganga.recipeapp.feature_recipe.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments.IngredientsFragment
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments.PreparationFragment
import com.robert.nganga.recipeapp.feature_recipe.presentation.ui.fragments.SummaryFragment

private const val NUM_TABS = 3

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
        FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> IngredientsFragment()
            1 -> PreparationFragment()
            else -> SummaryFragment()
        }
    }
}