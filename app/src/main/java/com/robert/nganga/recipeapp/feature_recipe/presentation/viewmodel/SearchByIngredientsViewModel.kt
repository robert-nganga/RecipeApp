package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.lifecycle.*
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.SearchRecipeByIngredients
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchByIngredientsViewModel@Inject constructor(
    savedStateHandle: SavedStateHandle,
   private val searchRecipeByIngredients: SearchRecipeByIngredients): ViewModel() {

    private var _result : MutableLiveData<Resource<List<RecipeByIngredients>>> = MutableLiveData()
    val result: LiveData<Resource<List<RecipeByIngredients>>> get() = _result



    fun getQuery(query: String){
        _result.value = Resource.loading()
        viewModelScope.launch {
            val result = searchRecipeByIngredients(query)
            _result.value = result
        }
    }

    companion object{
        const val CURRENT_QUERY = "current_query"
        const val DEFAULT_QUERY = ""
    }

}