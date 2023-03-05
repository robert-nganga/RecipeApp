package com.robert.nganga.recipeapp.feature_recipe.domain.model

import java.io.Serializable

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
): Serializable