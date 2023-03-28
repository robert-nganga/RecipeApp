package com.robert.nganga.recipeapp.feature_recipe.domain.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import kotlinx.coroutines.flow.Flow

interface SearchByIngredientsRepository {

    suspend fun searchByIngredients(ingredients: String): Flow<Resource<List<RecipeByIngredients>>>
}