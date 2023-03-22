package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.lifecycle.*
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel@Inject constructor(
    private val removeAllFavorites: RemoveAllFavorites,
    private val removeFavorite: RemoveFavorite,
    private val addFavorite: AddFavorite,
    getAllFavorites: GetAllFavorites): ViewModel() {


    val favorites = getAllFavorites().asLiveData()


    fun insertFavoriteRecipe(favorite: Recipe) = viewModelScope.launch {
        addFavorite(favorite.copy(isFavorite = true))
    }

    fun deleteFavoriteRecipe(favorite: Recipe) = viewModelScope.launch {
        removeFavorite(favorite.copy(isFavorite = false))
    }

    fun deleteAllFavoriteRecipes() = viewModelScope.launch {
        favorites.value?.data?.let {
            removeAllFavorites(it.map { favorite->
                favorite.copy(isFavorite = false)
            })
        }
    }

    fun toggleFavorite(recipe: Recipe) = viewModelScope.launch {
        if (recipe.isFavorite) {
            removeFavorite(recipe.copy(isFavorite = false))
        } else {
            addFavorite(recipe.copy(isFavorite = true))
        }
    }
}