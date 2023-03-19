package com.robert.nganga.recipeapp.feature_recipe.domain.model

data class MissedIngredient(
    val aisle: String,
    val extendedName: String,
    val id: Int,
    val image: String,
    val name: String,
    val original: String,
)
