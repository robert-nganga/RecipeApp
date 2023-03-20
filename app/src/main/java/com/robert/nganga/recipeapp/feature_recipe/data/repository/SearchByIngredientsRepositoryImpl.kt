package com.robert.nganga.recipeapp.feature_recipe.data.repository

import android.util.Log
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.data.remote.RecipeApi
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchByIngredientsRepository
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class SearchByIngredientsRepositoryImpl@Inject constructor(
        private val api: RecipeApi): SearchByIngredientsRepository {


    override suspend fun searchByIngredients(ingredients: String): List<RecipeByIngredients> {
        return api.searchByIngredients(ingredients).map { it.toRecipeByIngredients() }
    }


}