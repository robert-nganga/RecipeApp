package com.robert.nganga.recipeapp.feature_recipe.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {
    //create CRUD operations for the FavoriteEntity

    @Update
    suspend fun addFavorite(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe_table WHERE id = :id")
    fun getFavoriteById(id: Int): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe_table WHERE isFavorite = 1")
    fun getAllFavorites(): Flow<List<RecipeEntity>>

    @Update
    suspend fun removeFavorite(recipe: RecipeEntity)

    @Update
    suspend fun removeAllFavorites(recipes: List<RecipeEntity>)

}