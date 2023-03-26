package com.robert.nganga.recipeapp.feature_recipe.data.repository

import com.robert.nganga.recipeapp.feature_recipe.data.remote.RecipeApi
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchByIngredientsRepository
import javax.inject.Inject

class SearchByIngredientsRepositoryImpl@Inject constructor(
        private val api: RecipeApi): SearchByIngredientsRepository {


    override suspend fun searchByIngredients(ingredients: String): List<RecipeByIngredients> {
        return api.searchByIngredients(ingredients = ingredients).map { it.toRecipeByIngredients() }
    }


}