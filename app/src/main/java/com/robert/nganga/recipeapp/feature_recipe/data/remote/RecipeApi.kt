package com.robert.nganga.recipeapp.feature_recipe.data.remote

import com.robert.nganga.recipeapp.BuildConfig
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeByIngredientsDto
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeDto
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.RecipeResponseDto
import com.robert.nganga.recipeapp.feature_recipe.data.remote.dto.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int = 20,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("tags") tags: String = ""
    ): RecipeResponseDto

    @GET("{id}/information")
    suspend fun getRecipe(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): RecipeDto

    @GET("complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("number") number: Int = 20,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
    ): SearchResponseDto

    @GET("findByIngredients")
    suspend fun searchByIngredients(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("number") number: Int = 20,
        @Query("ingredients") ingredients: String,
    ): List<RecipeByIngredientsDto>


    companion object{
        const val BASE_URL = "https://api.spoonacular.com/recipes/"
    }


}