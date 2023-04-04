package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

data class SearchResponseDto(
    val number: Int,
    val offset: Int,
    val results: List<SearchResultDto>,
    val totalResults: Int
)