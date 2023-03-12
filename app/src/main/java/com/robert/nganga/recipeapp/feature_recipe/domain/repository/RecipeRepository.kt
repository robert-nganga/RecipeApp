package com.robert.nganga.recipeapp.feature_recipe.domain.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {


    fun getRandomRecipes(tag: String): Flow<Resource<List<Recipe>>>

    fun getRecipeById(id: Int): Flow<Resource<Recipe?>>

    suspend fun updateRecipe(recipe: Recipe)
}