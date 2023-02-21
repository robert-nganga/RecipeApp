package com.robert.nganga.recipeapp.feature_recipe.domain.model

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)