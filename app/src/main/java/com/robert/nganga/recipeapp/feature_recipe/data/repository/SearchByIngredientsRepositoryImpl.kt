package com.robert.nganga.recipeapp.feature_recipe.data.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.data.remote.RecipeApi
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchByIngredientsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class SearchByIngredientsRepositoryImpl@Inject constructor(
        private val api: RecipeApi): SearchByIngredientsRepository {


    override suspend fun searchByIngredients(ingredients: String): Flow<Resource<List<RecipeByIngredients>>> = flow{
        try {
            emit(Resource.loading())
            val response = api.searchByIngredients(ingredients = ingredients)
            emit(Resource.success(response.map { it.toRecipeByIngredients() }))
        } catch (e: HttpException) {
            emit(Resource.error(e.localizedMessage ?: "Unknown error"))
        } catch (e: IOException) {
            emit(Resource.error("Network error has occurred"))
        }
    }

}