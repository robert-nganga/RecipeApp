package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import com.robert.nganga.recipeapp.feature_recipe.domain.repository.FavoriteRepository
import javax.inject.Inject

class DeleteAllFavorites@Inject constructor(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke() = repository.deleteAllFavoriteRecipes()
}
