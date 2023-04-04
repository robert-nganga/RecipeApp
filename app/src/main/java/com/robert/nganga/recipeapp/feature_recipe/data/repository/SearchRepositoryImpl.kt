package com.robert.nganga.recipeapp.feature_recipe.data.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.data.remote.RecipeApi
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchRepository
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class SearchRepositoryImpl@Inject constructor(
    private val api: RecipeApi): SearchRepository {


    override suspend fun searchRecipes(query: String) = flow {
        try {
            emit(Resource.loading())
            val response = api.searchRecipes(query = query)
            emit(Resource.success(response.results.map { it.toSearchResult() }))
        } catch (e: HttpException) {
            emit(Resource.error(e.localizedMessage ?: "Unknown error"))
        } catch (e: IOException) {
            emit(Resource.error("Network error has occurred"))
        }

    }
}