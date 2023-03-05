package com.robert.nganga.recipeapp.feature_recipe.domain.model

import java.io.Serializable

data class Step(
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
): Serializable
