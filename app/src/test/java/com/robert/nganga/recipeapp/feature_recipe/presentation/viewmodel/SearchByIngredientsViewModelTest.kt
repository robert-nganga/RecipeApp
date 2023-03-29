package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.robert.nganga.recipeapp.feature_recipe.data.repository.FakeSearchRecipeByIngredientRepository
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.SearchRecipeByIngredients
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchByIngredientsViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchByIngredientsViewModel

    @Before
    fun setUp(){
        val fakeRepository = FakeSearchRecipeByIngredientRepository()
        val searchRecipeByIngredient = SearchRecipeByIngredients(fakeRepository)
        viewModel = SearchByIngredientsViewModel(searchRecipeByIngredient)
    }

    @Test
    fun `getSearchResults() returns a list of recipes`(){
        viewModel.getSearchResults("chicken")
        val result = viewModel.result.value
        val recipes = result?.data
        assertThat(recipes).isNotNull()
        assertThat(recipes?.size).isEqualTo(2)
    }
}