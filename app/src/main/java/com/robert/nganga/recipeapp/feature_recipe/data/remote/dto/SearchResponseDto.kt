package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

import com.robert.nganga.recipeapp.feature_recipe.domain.model.SearchResponse

data class SearchResponseDto(
    val number: Int,
    val offset: Int,
    val results: List<SearchResultDto>,
    val totalResults: Int
){
    fun toSearchResponse() = SearchResponse(
        number = number,
        offset = offset,
        results = results.map { it.toSearchResult() },
        totalResults = totalResults
    )
}