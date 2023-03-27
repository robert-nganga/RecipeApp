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
    private lateinit var recipeEntityDao: RecipeEntityDao
    private lateinit var recipeEntity: RecipeEntity

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipeDatabase::class.java
        ).addTypeConverter(Converters(GsonParser(Gson()))).allowMainThreadQueries().build()
        favoriteDao = database.favoriteDao()
        recipeEntityDao = database.recipeDao()
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
            isFavorite = false
        )
        runBlocking {
            recipeEntityDao.insertRecipe(recipeEntity)
        }
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun addFavorite() = runBlocking {
        val new = recipeEntity.copy(isFavorite = true)
        favoriteDao.addFavorite(new)
        val favorite = favoriteDao.getFavoriteById(1).asLiveData().getOrAwaitValue()
        assertThat(favorite[0]).isEqualTo(new)
    }

    @Test
    fun getAllFavorites() = runBlocking {
        val new = recipeEntity.copy(isFavorite = true)
        favoriteDao.addFavorite(new)
        val favorites = favoriteDao.getAllFavorites().asLiveData().getOrAwaitValue()
        assertThat(favorites).contains(new)
    }

    @Test
    fun removeFavorite() = runBlocking {
        val new = recipeEntity.copy(isFavorite = true)
        favoriteDao.addFavorite(new)
        val added = favoriteDao.getFavoriteById(1).asLiveData().getOrAwaitValue()
        assertThat(added[0]).isEqualTo(new)
        favoriteDao.removeFavorite(new.copy(isFavorite = false))
        val favorites = favoriteDao.getAllFavorites().asLiveData().getOrAwaitValue()
        assertThat(favorites).isEmpty()
    }
}