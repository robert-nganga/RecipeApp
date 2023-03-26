package com.robert.nganga.recipeapp.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.robert.nganga.recipeapp.feature_recipe.data.local.Converters
import com.robert.nganga.recipeapp.feature_recipe.data.local.RecipeDatabase
import com.robert.nganga.recipeapp.feature_recipe.data.local.dao.FavoriteDao
import com.robert.nganga.recipeapp.feature_recipe.data.local.dao.RecipeEntityDao
import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.RecipeEntity
import com.robert.nganga.recipeapp.feature_recipe.data.util.GsonParser
import com.robert.nganga.recipeapp.feature_recipe.domain.model.AnalyzedInstruction
import com.robert.nganga.recipeapp.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FavoriteDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: RecipeDatabase
    private lateinit var favoriteDao: FavoriteDao
    private lateinit var recipeEntity: RecipeEntity

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipeDatabase::class.java
        ).addTypeConverter(Converters(GsonParser(Gson()))).allowMainThreadQueries().build()
        favoriteDao = database.favoriteDao()
        recipeEntity = RecipeEntity(
            aggregateLikes = 1,
            analyzedInstructions = listOf(AnalyzedInstruction(name = "name1", steps = listOf())),
            cookingMinutes = 1,
            cuisines = listOf("cuisine1"),
            dairyFree = true,
            diets = listOf("diet1"),
            dishTypes = listOf("dishType1"),
            extendedIngredients = listOf(),
            glutenFree = true,
            id = 1,
            image = "image1",
            imageType = "imageType1",
            instructions = "instructions1",
            readyInMinutes = 1,
            servings = 1,
            sourceName = "sourceName1",
            sourceUrl = "sourceUrl1",
            summary = "summary1",
            title = "title1",
            vegan = true,
            vegetarian = true,
            tag = "tag1",
            timeStamp = "timeStamp1",
            isFavorite = true
        )
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun addFavorite() = runBlocking {
        favoriteDao.addFavorite(recipeEntity)
        val favorite = favoriteDao.getFavoriteById(1).asLiveData().getOrAwaitValue()
        assertThat(favorite).isEqualTo(recipeEntity)
    }

    @Test
    fun getAllFavorites() = runBlocking {
        val recipes = listOf( recipeEntity, recipeEntity.copy(id = 2))
        favoriteDao.addFavorite(recipeEntity)
        favoriteDao.addFavorite(recipeEntity.copy(id = 2))
        val favorites = favoriteDao.getAllFavorites().asLiveData().getOrAwaitValue()
        assertThat(favorites).isEqualTo(recipes)
    }

    @Test
    fun removeFavorite() = runBlocking {
        favoriteDao.addFavorite(recipeEntity)
        favoriteDao.removeFavorite(recipeEntity)
        val favorites = favoriteDao.getAllFavorites().asLiveData().getOrAwaitValue()
        assertThat(favorites).isEmpty()
    }
}