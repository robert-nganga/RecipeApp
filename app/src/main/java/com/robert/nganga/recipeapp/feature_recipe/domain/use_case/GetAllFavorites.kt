package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import com.robert.nganga.recipeapp.feature_recipe.domain.repository.FavoriteRepository
import javax.inject.Inject

class GetAllFavorites@Inject constructor(
    private val repository: FavoriteRepository
) {

    operator fun invoke() = repository.getFavoriteRecipes()

}