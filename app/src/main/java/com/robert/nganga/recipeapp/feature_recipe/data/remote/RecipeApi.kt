package com.robert.nganga.recipeapp.feature_recipe.data.remote

import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeDto
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("random")
    suspend fun getRecipes(
        @Query("number") number: Int,
        @Query("tags") tags: String
    ): RecipeResponse

    @GET("{id}/information")
    suspend fun getRecipe(
        @Query("id") id: Int
    ): RecipeDto

}