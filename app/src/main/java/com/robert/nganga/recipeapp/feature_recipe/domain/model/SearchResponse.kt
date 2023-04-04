package com.robert.nganga.recipeapp.feature_recipe.domain.model

data class SearchResponse (
    val number: Int,
    val offset: Int,
    val results: List<SearchResult>,
    val totalResults: Int
)