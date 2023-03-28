package com.robert.nganga.recipeapp.feature_recipe.data.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchByIngredientsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSearchRecipeByIngredientRepository: SearchByIngredientsRepository {

    private val recipes = mutableListOf<RecipeByIngredients>()
    private var shouldReturnNetworkError = false


    init {
        recipes.add(RecipeByIngredients(id = 1, title = "Recipe 1", image = "image1", usedIngredientCount = 1, missedIngredientCount = 1, likes = 1, imageType = "imageType1", missedIngredients = emptyList(), usedIngredients = emptyList(), unusedIngredients = emptyList()))
        recipes.add(RecipeByIngredients(id = 2, title = "Recipe 2", image = "image2", usedIngredientCount = 2, missedIngredientCount = 2, likes = 2, imageType = "imageType2", missedIngredients = emptyList(), usedIngredients = emptyList(), unusedIngredients = emptyList()))
    }

    override suspend fun searchByIngredients(ingredients: String) = flow {
        if (shouldReturnNetworkError) {
            emit(Resource.error("Network error has occurred"))
        } else {
            emit(Resource.success(recipes.toList()))
        }
    }
}