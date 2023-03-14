package com.robert.nganga.recipeapp.feature_recipe.domain.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.RecipeEntity
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Favorite
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavoriteRecipes(): Flow<Resource<List<Recipe>>>

    fun getFavoriteRecipeById(id: Int): Flow<Resource<Recipe?>>

    suspend fun addFavorite(favorite: Recipe)

    suspend fun removeFavorite(favorite: Recipe)

    suspend fun removeAllFavorite(favorites: List<Recipe>)

}