package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel@Inject constructor(
    private val deleteAllFavorites: DeleteAllFavorites,
    private val deleteFavorite: DeleteFavorite,
    private val getFavoriteById: GetFavoriteById,
    private val insertFavorite: InsertFavorite,
    private val getAllFavorites: GetAllFavorites): ViewModel() {

    val favorite: String = "Favorite"
}