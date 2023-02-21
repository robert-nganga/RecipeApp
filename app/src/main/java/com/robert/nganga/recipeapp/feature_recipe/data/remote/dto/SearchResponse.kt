package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

import com.robert.nganga.recipeapp.feature_recipe.domain.model.Search

data class SearchResponse(
    val number: Int,
    val offset: Int,
    val results: List<Search>,
    val totalResults: Int
)