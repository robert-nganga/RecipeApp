package com.robert.nganga.recipeapp.feature_recipe.data.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.flow

class FakeRecipeRepository: RecipeRepository {

    private val recipes = mutableListOf<Recipe>()
    private var shouldReturnNetworkError = false

    init {
        recipes.add(
            Recipe(
                id = 1,
                title = "Recipe 1",
                image = "image1",
                imageType = "imageType1",
                summary = "summary1",
                cuisines = emptyList(),
                dishTypes = emptyList(),
                diets = emptyList(),
                instructions = "instructions1",
                analyzedInstructions = emptyList(),
                extendedIngredients = emptyList(),
                vegetarian = true,
                vegan = true,
                glutenFree = true,
                dairyFree = true,
                aggregateLikes = 1,
                sourceName = "sourceName1",
                servings = 1,
                sourceUrl = "sourceUrl1",
                isFavorite = true,
                cookingMinutes = 1,
                readyInMinutes = 1,
                tag = "myTag",
                timeStamp = "timeStamp1"
            )
        )
        recipes.add(
            Recipe(
                id = 2,
                title = "Recipe 2",
                image = "image2",
                imageType = "imageType2",
                summary = "summary2",
                cuisines = emptyList(),
                dishTypes = emptyList(),
                diets = emptyList(),
                instructions = "instructions2",
                analyzedInstructions = emptyList(),
                extendedIngredients = emptyList(),
                vegetarian = true,
                vegan = true,
                glutenFree = true,
                dairyFree = true,
                aggregateLikes = 2,
                sourceName = "sourceName2",
                readyInMinutes = 2,
                servings = 2,
                sourceUrl = "sourceUrl2",
                isFavorite = true,
                cookingMinutes = 2,
                tag = "myTag",
                timeStamp = "timeStamp2"
            )
        )
    }

    override fun getRandomRecipes(tag: String)= flow {
        if (shouldReturnNetworkError) {
            emit(Resource.error("Network error has occurred"))
        } else {
            val list = recipes.filter { it.tag == tag }
            emit(Resource.success(list))
        }
    }

    override fun getRecipeById(id: Int) = flow {
        if (shouldReturnNetworkError) {
            emit(Resource.error("Network error has occurred"))
        } else {
            emit(Resource.success(recipes.first { it.id == id }))
        }
    }
    override suspend fun updateRecipe(recipe: Recipe) {
        val recipeIndex = recipes.indexOfFirst { it.id == recipe.id }
        if (recipeIndex > -1) {
            recipes[recipeIndex] = recipe
        }
    }
}