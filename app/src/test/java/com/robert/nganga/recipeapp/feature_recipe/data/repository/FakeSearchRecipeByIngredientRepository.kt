package com.robert.nganga.recipeapp.feature_recipe.data.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchByIngredientsRepository
import kotlinx.coroutines.flow.Flow

class FakeSearchRecipeByIngredientRepository: SearchByIngredientsRepository {

    private val recipes = mutableListOf<RecipeByIngredients>()


    init {
    }

    override suspend fun searchByIngredients(ingredients: String): Flow<Resource<List<RecipeByIngredients>>> {
        TODO("Not yet implemented")
    }
}