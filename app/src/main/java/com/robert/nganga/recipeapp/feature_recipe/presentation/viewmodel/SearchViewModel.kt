package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.SearchResult
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.SearchRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel@Inject constructor(
    private val searchRecipe: SearchRecipe
): ViewModel(){

    private var _searchResults: MutableLiveData<Resource<List<SearchResult>>> = MutableLiveData()
    val searchResults: LiveData<Resource<List<SearchResult>>> get() = _searchResults


    private var currentQuery = ""
    fun getSearchResults(query: String){
        if (query == currentQuery) return
        currentQuery = query
        _searchResults.postValue(Resource.loading())
        viewModelScope.launch {
            searchRecipe(query).collect{ response->
                _searchResults.postValue(response)
            }
        }
    }

}