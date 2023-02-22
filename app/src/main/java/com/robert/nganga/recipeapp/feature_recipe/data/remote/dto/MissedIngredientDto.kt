package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

data class MissedIngredientDto(
    val aisle: String,
    val amount: Double,
    val extendedName: String,
    val id: Int,
    val image: String,
    val meta: List<String>,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String,
    val unitLong: String,
    val unitShort: String
)