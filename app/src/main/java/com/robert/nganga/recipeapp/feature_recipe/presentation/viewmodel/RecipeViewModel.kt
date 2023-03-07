package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.GetRandomRecipes
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.GetRecipeById
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel@Inject constructor(
    private val getRandomRecipes: GetRandomRecipes,
    private val getRecipeById: GetRecipeById,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _tag = savedStateHandle.getLiveData(CURRENT_TAG, DEFAULT_TAG)

    private var _id = MutableLiveData<Int>()

    val recipes = _tag.switchMap { tag ->
        getRandomRecipes(tag).asLiveData()
    }

    val recipe = _id.switchMap { id ->
        getRecipeById(id).asLiveData()
    }


    fun getTags(tag:String){
        val currentTag = if (tag == "all") DEFAULT_TAG else tag
        if (currentTag != _tag.value){
            _tag.value = tag
            Log.i("RecipeViewModel", "Fetched data with tag: $tag")
        }

    }

    fun getIds(id:Int){
        _id.value = id
    }

    companion object{
        const val CURRENT_TAG = "current_tag"
        const val DEFAULT_TAG = ""
    }
}