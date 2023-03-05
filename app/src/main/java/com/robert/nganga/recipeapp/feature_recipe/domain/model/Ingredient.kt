package com.robert.nganga.recipeapp.feature_recipe.domain.model

import java.io.Serializable

class Ingredient (
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String
    ): Serializable