package com.robert.nganga.recipeapp.feature_recipe.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe_table WHERE id = :id")
    fun getRecipe(id: Int): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe_table WHERE tag = :tag")
    fun getRecipes(tag: String): Flow<List<RecipeEntity>>

    @Query("DELETE FROM recipe_table WHERE tag = :tag")
    suspend fun deleteRecipes(tag: String)

    @Query("DELETE FROM recipe_table WHERE id = :id")
    suspend fun deleteRecipe(id: Int)

}