package com.robert.nganga.recipeapp.feature_recipe.domain.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavoriteRecipes(): Flow<Resource<List<Favorite>>>

    fun getFavoriteRecipe(id: Int): Flow<Resource<Favorite?>>

    suspend fun insertFavoriteRecipe(favorite: Favorite)

    suspend fun deleteFavoriteRecipe(favorite: Favorite)

    suspend fun deleteAllFavoriteRecipes()

}