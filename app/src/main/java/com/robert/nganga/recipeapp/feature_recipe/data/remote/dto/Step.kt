package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val length: Length,
    val number: Int,
    val step: String
)