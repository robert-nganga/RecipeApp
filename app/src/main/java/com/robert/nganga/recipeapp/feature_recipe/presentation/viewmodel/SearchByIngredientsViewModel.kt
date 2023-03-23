package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.lifecycle.*
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.SearchRecipeByIngredients
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchByIngredientsViewModel@Inject constructor(
   private val searchRecipeByIngredients: SearchRecipeByIngredients): ViewModel() {

    private val _query : MutableLiveData<String> = MutableLiveData()
    val query : LiveData<String> = _query

    val result = _query.switchMap { query ->
        searchRecipeByIngredients(query).asLiveData()
    }

    fun updateIngredients(query: String){
        _query.value = query

    }

}