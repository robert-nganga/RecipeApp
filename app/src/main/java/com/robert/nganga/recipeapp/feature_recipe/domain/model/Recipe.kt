package com.robert.nganga.recipeapp.feature_recipe.domain.model

import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.RecipeEntity


data class Recipe(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cookingMinutes: Int,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val glutenFree: Boolean,
    val id: Int,
    val image: String?,
    val imageType: String?,
    val instructions: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val timeStamp: String?,
    val isFavorite: Boolean = false
){
    fun toFavorite() = Favorite(
        aggregateLikes = aggregateLikes,
        cookingMinutes = cookingMinutes,
        cuisines = cuisines,
        dairyFree = dairyFree,
        diets = diets,
        dishTypes = dishTypes,
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
        analyzedInstructions = analyzedInstructions,
        extendedIngredients = extendedIngredients,
    )

}