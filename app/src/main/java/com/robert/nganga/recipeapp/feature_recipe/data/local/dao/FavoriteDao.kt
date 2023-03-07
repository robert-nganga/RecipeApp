package com.robert.nganga.recipeapp.feature_recipe.data.local.dao

import androidx.room.*
import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {
    //create CRUD operations for the FavoriteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_table")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite_table WHERE id = :id")
    fun getFavorite(id: Int): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAllFavorites()
}