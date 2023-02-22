package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

import com.robert.nganga.recipeapp.feature_recipe.domain.model.AnalyzedInstruction
import com.robert.nganga.recipeapp.feature_recipe.domain.model.ExtendedIngredient
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe

data class RecipeDto(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val cookingMinutes: Int,
    val creditsText: String,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Int,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val lowFodmap: Boolean,
    val occasions: List<String>,
    val originalId: Any,
    val preparationMinutes: Int,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int
){
    fun toRecipe():Recipe {
        return Recipe(
            aggregateLikes = aggregateLikes,
            analyzedInstructions = analyzedInstructions,
            cookingMinutes = cookingMinutes,
            cuisines = cuisines,
            dairyFree = dairyFree,
            diets = diets,
            dishTypes = dishTypes,
            extendedIngredients = extendedIngredients,
            glutenFree = glutenFree,
            id = id,
            image = image,
            imageType = imageType,
            instructions = instructions,
            readyInMinutes = readyInMinutes,
            servings = servings,
            sourceName = sourceName,
            sourceUrl = sourceUrl,
            summary = summary,
            title = title,
            vegan = vegan,
            vegetarian = vegetarian
        )
    }
}