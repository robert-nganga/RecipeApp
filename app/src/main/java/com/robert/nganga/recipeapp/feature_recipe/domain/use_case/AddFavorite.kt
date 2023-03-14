package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import javax.inject.Inject

class AddFavorite@Inject constructor(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(favorite: Recipe) = repository.addFavorite(favorite)

}