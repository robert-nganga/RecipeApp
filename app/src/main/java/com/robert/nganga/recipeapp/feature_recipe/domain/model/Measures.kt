package com.robert.nganga.recipeapp.feature_recipe.domain.model

import java.io.Serializable

data class Measures(
    val us: Us,
    val metric: Metric,
): Serializable
