package com.robert.nganga.recipeapp.feature_recipe.domain.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun searchRecipes(query: String): Flow<Resource<List<SearchResult>>>
}