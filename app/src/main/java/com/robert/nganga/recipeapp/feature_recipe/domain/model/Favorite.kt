package com.robert.nganga.recipeapp.feature_recipe.domain.model

import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.FavoriteEntity

data class Favorite(
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
){
    fun toFavoriteEntity() = FavoriteEntity(
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
    )
}