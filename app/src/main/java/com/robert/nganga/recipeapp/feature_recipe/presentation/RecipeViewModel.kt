package com.robert.nganga.recipeapp.feature_recipe.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.GetRandomRecipes
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.GetRecipeById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.cache
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel@Inject constructor(
    private val getRandomRecipes: GetRandomRecipes,
    private val getRecipeById: GetRecipeById,
) : ViewModel() {

    private var _tag = MutableLiveData<String>()
    private var _id = MutableLiveData<Int>()

    val recipes = _tag.switchMap { tag ->
        getRandomRecipes(tag).asLiveData()
    }

    val recipe = _id.switchMap { id ->
        getRecipeById(id).asLiveData()
    }


    private var myTag = ""
    fun getTags(tag:String){
        if (myTag == tag) return
        myTag = tag
        _tag.value = tag
    }

    fun getIds(id:Int){
        _id.value = id
    }
}