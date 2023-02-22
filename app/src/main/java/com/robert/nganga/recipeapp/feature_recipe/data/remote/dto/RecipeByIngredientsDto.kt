package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

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
)