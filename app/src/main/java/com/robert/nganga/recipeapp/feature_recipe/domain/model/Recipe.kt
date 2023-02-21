package com.robert.nganga.recipeapp.feature_recipe.domain.model

data class Recipe(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cuisines: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val preparationMinutes: Int,
    val readyInMinutes: Int,
    val sourceUrl: String,
    val summary: String,
    val title: String,

    )