package com.robert.nganga.recipeapp.feature_recipe.data.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFavoriteRepository: FavoriteRepository{

    private val recipes = mutableListOf<Recipe>()

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
                servings = 2,
                sourceUrl = "sourceUrl2",
                isFavorite = true,
                cookingMinutes = 2,
                readyInMinutes = 2,
                tag = "myTag",
                timeStamp = "timeStamp2"
            )
        )
        recipes.add(
            Recipe(
                id = 3,
                title = "Recipe 3",
                image = "image3",
                imageType = "imageType3",
                summary = "summary3",
                cuisines = emptyList(),
                dishTypes = emptyList(),
                diets = emptyList(),
                instructions = "instructions3",
                analyzedInstructions = emptyList(),
                extendedIngredients = emptyList(),
                vegetarian = true,
                vegan = true,
                glutenFree = true,
                dairyFree = true,
                aggregateLikes = 3,
                sourceName = "sourceName3",
                servings = 3,
                sourceUrl = "sourceUrl3",
                isFavorite = true,
                cookingMinutes = 3,
                readyInMinutes = 3,
                tag = "myTag",
                timeStamp = "timeStamp3"
            )
        )
    }

    override fun getFavoriteRecipes() = flow {
        emit(Resource.success(recipes.filter { it.isFavorite }))
    }

    override fun getFavoriteRecipeById(id: Int) = flow {
        emit(Resource.success(recipes.first { it.id == id }))
    }

    override suspend fun addFavorite(favorite: Recipe) {
        recipes[recipes.indexOf(recipes.first { it.id == favorite.id })] = favorite
    }

    override suspend fun removeFavorite(favorite: Recipe) {
        recipes[recipes.indexOf(recipes.first { it.id == favorite.id })] = favorite
    }

    override suspend fun removeAllFavorite(favorites: List<Recipe>) {
        favorites.forEach { favorite ->
            recipes[recipes.indexOf(recipes.first { it.id == favorite.id })] = favorite
        }
    }

}