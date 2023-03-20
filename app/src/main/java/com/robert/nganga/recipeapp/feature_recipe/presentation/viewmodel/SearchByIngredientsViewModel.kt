package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.SearchRecipeByIngredients
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchByIngredientsViewModel@Inject constructor(
    savedStateHandle: SavedStateHandle,
   private val searchRecipeByIngredients: SearchRecipeByIngredients): ViewModel() {

    private val _query = savedStateHandle.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val result = _query.switchMap { query ->
        searchRecipeByIngredients(query).asLiveData()
    }



    fun getQuery(query: String){
        _query.value = query
    }

    companion object{
        const val CURRENT_QUERY = "current_query"
        const val DEFAULT_QUERY = ""
    }

}