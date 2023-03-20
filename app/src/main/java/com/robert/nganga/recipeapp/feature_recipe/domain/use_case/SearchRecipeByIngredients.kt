package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import android.util.Log
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchByIngredientsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class SearchRecipeByIngredients@Inject constructor(
        private val repository: SearchByIngredientsRepository
) {
    operator fun invoke(ingredients: String) :Flow<Resource<List<RecipeByIngredients>>> = flow{
        try {
            emit(Resource.loading())
            val response = repository.searchByIngredients(ingredients)
            emit(Resource.success(response))
        } catch (e: HttpException) {
            emit(Resource.error(e.localizedMessage ?: "Unknown error"))
        } catch (e: IOException) {
            emit(Resource.error("Network error has occurred"))
        }
    }
}