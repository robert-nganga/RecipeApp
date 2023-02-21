package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)