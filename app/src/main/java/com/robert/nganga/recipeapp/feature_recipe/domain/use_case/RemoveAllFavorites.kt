package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import javax.inject.Inject

class RemoveAllFavorites@Inject constructor(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(favorites: List<Recipe>) = repository.removeAllFavorite(favorites)
}
