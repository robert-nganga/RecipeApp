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
    suspend operator fun invoke(ingredients: String) = repository.searchByIngredients(ingredients)
}