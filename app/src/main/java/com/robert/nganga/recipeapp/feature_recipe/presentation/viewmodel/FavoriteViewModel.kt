package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel@Inject constructor(
    private val removeAllFavorites: RemoveAllFavorites,
    private val removeFavorite: RemoveFavorite,
    private val getFavoriteById: GetFavoriteById,
    private val addFavorite: AddFavorite,
    private val getAllFavorites: GetAllFavorites): ViewModel() {

    val favorite: String = "Favorite"

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
}