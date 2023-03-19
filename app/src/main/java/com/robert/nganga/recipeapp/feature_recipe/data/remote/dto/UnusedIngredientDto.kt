package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

import com.robert.nganga.recipeapp.feature_recipe.domain.model.UnusedIngredient

data class UnusedIngredientDto(
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
){
    fun toUnusedIngredient() = UnusedIngredient(
        aisle = aisle,
        amount = amount,
        id = id,
        image = image,
        name = name,
        original = original
    )
}