package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchByIngredientsRepository
import javax.inject.Inject

class SearchRecipeByIngredients@Inject constructor(
        private val repository: SearchByIngredientsRepository
) {
    suspend operator fun invoke(query: String) = repository.searchByIngredients(query)
}