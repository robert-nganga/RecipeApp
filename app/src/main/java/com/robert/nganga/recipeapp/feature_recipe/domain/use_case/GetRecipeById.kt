package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import com.robert.nganga.recipeapp.feature_recipe.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeById@Inject constructor(
    private val repository: RecipeRepository,
) {
    operator fun invoke(id: Int) = repository.getRecipeById(id)
}