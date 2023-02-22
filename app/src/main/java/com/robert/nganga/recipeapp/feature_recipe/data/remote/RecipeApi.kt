package com.robert.nganga.recipeapp.feature_recipe.data.remote

import com.robert.nganga.recipeapp.BuildConfig
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeByIngredientsDto
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeDto
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("tags") tags: String
    ): RecipeResponseDto

    @GET("{id}/information")
    suspend fun getRecipe(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): RecipeDto

//    @GET("complexSearch")
//    suspend fun searchRecipes(
//        @Query("query") query: String,
//        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
//    ): SearchResponse

    @GET("findByIngredients")
    suspend fun searchByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): List<RecipeByIngredientsDto>


}