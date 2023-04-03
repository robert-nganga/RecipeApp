package com.robert.nganga.recipeapp.feature_recipe.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.robert.nganga.recipeapp.feature_recipe.data.repository.FakeFavoriteRepository
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.AddFavorite
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.GetAllFavorites
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.RemoveAllFavorites
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.RemoveFavorite
import com.robert.nganga.recipeapp.getOrAwaitValueTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var recipe :Recipe
    private lateinit var favorite: Recipe

    @Before
    fun setUp() {
        val fakeRepository = FakeFavoriteRepository()
        val getAllFavorites = GetAllFavorites(fakeRepository)
        val addFavorite = AddFavorite(fakeRepository)
        val removeFavorite = RemoveFavorite(fakeRepository)
        val removeAllFavorites = RemoveAllFavorites(fakeRepository)
        viewModel = FavoriteViewModel(removeAllFavorites, removeFavorite, addFavorite, getAllFavorites)
        recipe = Recipe( id = 4, title = "Recipe 4", image = "image4", imageType = "imageType4", summary = "summary4", cuisines = emptyList(), dishTypes = emptyList(), diets = emptyList(), instructions = "instructions4", analyzedInstructions = emptyList(), extendedIngredients = emptyList(), vegetarian = true, vegan = true, glutenFree = true, dairyFree = true, aggregateLikes = 4, sourceName = "sourceName4", servings = 4, sourceUrl = "sourceUrl4", isFavorite = false, cookingMinutes = 4, readyInMinutes = 4, tag = "myTag", timeStamp = "timeStamp4")
        favorite = Recipe(id = 3, title = "Recipe 3", image = "image3", imageType = "imageType3", summary = "summary3", cuisines = emptyList(), dishTypes = emptyList(), diets = emptyList(), instructions = "instructions3", analyzedInstructions = emptyList(), extendedIngredients = emptyList(), vegetarian = true, vegan = true, glutenFree = true, dairyFree = true, aggregateLikes = 3, sourceName = "sourceName3", servings = 3, sourceUrl = "sourceUrl3", isFavorite = true, cookingMinutes = 3, readyInMinutes = 3, tag = "myTag", timeStamp = "timeStamp3")
    }

    @Test
    fun `getFavorites() returns all favorite recipes`() {
        val all = viewModel.favorites.getOrAwaitValueTest().data
        assertThat(all).isNotNull()
        assertThat(all?.size).isEqualTo(3)
    }

    @Test
    fun insertFavoriteRecipe() {
        viewModel.insertFavoriteRecipe(recipe)
        val all = viewModel.favorites.getOrAwaitValueTest().data
        assertThat(all).isNotNull()
        assertThat(all?.size).isEqualTo(4)
    }

    @Test
    fun deleteFavoriteRecipe() {
        viewModel.deleteFavoriteRecipe(favorite)
        val all = viewModel.favorites.getOrAwaitValueTest().data
        assertThat(all).isNotNull()
        assertThat(all?.size).isEqualTo(2)
    }


    @Test
    fun toggleFavorite() {
        viewModel.toggleFavorite(recipe)
        val all = viewModel.favorites.getOrAwaitValueTest().data
        assertThat(all).isNotNull()
        assertThat(all?.size).isEqualTo(4)
    }
}