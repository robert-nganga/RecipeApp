package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.robert.nganga.recipeapp.feature_recipe.data.repository.FakeRecipeRepository
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.GetRandomRecipes
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.GetRecipeById
import com.robert.nganga.recipeapp.getOrAwaitValueTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RecipeViewModel

    @Before
    fun setUp(){
        val fakeRepository = FakeRecipeRepository()
        val getRandomRecipes = GetRandomRecipes(fakeRepository)
        val getRecipeById = GetRecipeById(fakeRepository)
        val savedStateHandle = SavedStateHandle(mapOf("current_tag" to "all"))
        viewModel = RecipeViewModel(getRandomRecipes, getRecipeById, savedStateHandle)
    }

    @Test
    fun `updateCategory() updates the tag`(){
        viewModel.updateCategory("myTag")
        val tag = viewModel.tag.value
        assertThat(tag).isEqualTo("myTag")
    }

    @Test
    fun `updateCategory() updates the recipes if the new tag is different from the old tag`(){
        viewModel.updateCategory("myTag")
        val recipes = viewModel.recipes.getOrAwaitValueTest().data
        assertThat(recipes).isNotNull()
        val tag = viewModel.tag.value
        assertThat(tag).isEqualTo("myTag")
        assertThat(recipes?.size).isEqualTo(2)
    }

    @Test
    fun `updateId() gets the recipe with the given id`(){
        viewModel.getRecipe(1)
        val recipe = viewModel.recipe.getOrAwaitValueTest().data
        assertThat(recipe).isNotNull()
        assertThat(recipe?.id).isEqualTo(1)
    }

    @Test
    fun `retry() updates the recipes`(){
        viewModel.retry("myTag")
        val recipes = viewModel.recipes.getOrAwaitValueTest().data
        assertThat(recipes).isNotNull()
        val tag = viewModel.tag.value
        assertThat(tag).isEqualTo("myTag")
        assertThat(recipes?.size).isEqualTo(2)
    }

}