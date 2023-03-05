package com.robert.nganga.recipeapp.feature_recipe.domain.model

import java.io.Serializable

data class Us(
    val amount: Double,
    val unitLong: String,
    val unitShort: String
): Serializable