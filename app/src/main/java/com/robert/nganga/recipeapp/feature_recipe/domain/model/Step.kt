package com.robert.nganga.recipeapp.feature_recipe.domain.model


data class Step(
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)
