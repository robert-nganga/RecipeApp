package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Favorite
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel@Inject constructor(
    private val deleteAllFavorites: DeleteAllFavorites,
    private val deleteFavorite: DeleteFavorite,
    private val getFavoriteById: GetFavoriteById,
    private val insertFavorite: InsertFavorite,
    private val getAllFavorites: GetAllFavorites): ViewModel() {

    val favorite: String = "Favorite"

    val favorites = getAllFavorites().asLiveData()

    fun insertFavoriteRecipe(favorite: Favorite) = viewModelScope.launch {
        insertFavorite(favorite)
    }

    fun deleteFavoriteRecipe(favorite: Favorite) = viewModelScope.launch {
        deleteFavorite(favorite)
    }

    fun deleteAllFavoriteRecipes() = viewModelScope.launch {
        deleteAllFavorites()
    }
}