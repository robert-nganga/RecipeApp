package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRecipe@Inject constructor(
        private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String) = repository.searchRecipes(query)
}