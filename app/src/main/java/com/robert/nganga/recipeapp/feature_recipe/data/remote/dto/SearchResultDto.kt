package com.robert.nganga.recipeapp.feature_recipe.data.remote.dto

import com.robert.nganga.recipeapp.feature_recipe.domain.model.SearchResult

data class SearchResultDto(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
){
    fun toSearchResult() = SearchResult(
        id = id,
        image = image,
        imageType = imageType,
        title = title
    )
}
