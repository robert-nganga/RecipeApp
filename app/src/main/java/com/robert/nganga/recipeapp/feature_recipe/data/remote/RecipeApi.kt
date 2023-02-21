package com.robert.nganga.recipeapp.feature_recipe.data.remote

import com.robert.nganga.recipeapp.BuildConfig
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeDto
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeResponse
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.SearchResponse
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("tags") tags: String
    ): RecipeResponse

    @GET("{id}/information")
    suspend fun getRecipe(
        @Query("id") id: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): RecipeDto

    @GET("complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): SearchResponse

    @GET("findByIngredients")
    suspend fun searchByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): List<RecipeByIngredients>


}