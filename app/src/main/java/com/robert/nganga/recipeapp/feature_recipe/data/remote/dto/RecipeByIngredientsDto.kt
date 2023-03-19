package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients

data class RecipeByIngredientsDto(
    val id: Int,
    val image: String,
    val imageType: String,
    val likes: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<MissedIngredientDto>,
    val title: String,
    val unusedIngredients: List<UnusedIngredientDto>,
    val usedIngredientCount: Int,
    val usedIngredients: List<UsedIngredientDto>
){
    fun toRecipeByIngredients() = RecipeByIngredients(
        id = id,
        image = image,
        imageType = imageType,
        likes = likes,
        missedIngredientCount = missedIngredientCount,
        missedIngredients = missedIngredients.map { it.toMissedIngredient() },
        title = title,
        unusedIngredients = unusedIngredients.map { it.toUnusedIngredient() },
        usedIngredientCount = usedIngredientCount,
        usedIngredients = usedIngredients.map { it.toUsedIngredient() }
    )
}