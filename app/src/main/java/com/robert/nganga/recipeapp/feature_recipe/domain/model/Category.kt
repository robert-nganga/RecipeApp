package com.robert.nganga.recipeapp.feature_recipe.domain.model

import java.io.Serializable

data class Category (
    val isChecked: Boolean = false,
    val title: String,
    val image: Int
    ): Serializable