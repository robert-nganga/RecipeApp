package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import com.robert.nganga.recipeapp.feature_recipe.domain.repository.RecipeRepository

class GetRandomRecipes(private val repository: RecipeRepository) {
    operator fun invoke(tag: String) = repository.getRandomRecipes(tag)
}
