package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

data class UsedIngredientDto(
    val aisle: String,
    val amount: Double,
    val id: Int,
    val image: String,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String,
    val unitLong: String,
    val unitShort: String
)