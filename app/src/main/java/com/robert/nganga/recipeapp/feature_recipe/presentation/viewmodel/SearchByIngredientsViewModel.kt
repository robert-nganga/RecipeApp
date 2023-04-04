package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.lifecycle.*
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.RecipeByIngredients
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.SearchRecipeByIngredients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchByIngredientsViewModel@Inject constructor(
   private val searchRecipeByIngredients: SearchRecipeByIngredients): ViewModel() {

    private val _query : MutableLiveData<String> = MutableLiveData()
    val query : LiveData<String> = _query

    private var _result: MutableLiveData<Resource<List<RecipeByIngredients>>> = MutableLiveData()
    val result: LiveData<Resource<List<RecipeByIngredients>>> get() = _result





    fun getSearchResults(query: String){
        _query.value = query
        _result.postValue(Resource.loading())
        viewModelScope.launch {
            searchRecipeByIngredients(query).collect{ response->
                _result.postValue(response)
            }
        }
    }

}