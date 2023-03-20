package com.robert.nganga.recipeapp.feature_recipe.presentation

import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String = "",
    val selectedCategory: String = "all",
)