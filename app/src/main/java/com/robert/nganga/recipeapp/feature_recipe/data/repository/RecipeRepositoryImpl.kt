package com.robert.nganga.recipeapp.feature_recipe.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.robert.nganga.recipeapp.feature_recipe.data.local.RecipeDatabase
import com.robert.nganga.recipeapp.feature_recipe.data.remote.RecipeApi
import com.robert.nganga.recipeapp.feature_recipe.data.util.networkBoundResource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class RecipeRepositoryImpl@Inject constructor(
    private val api: RecipeApi,
    private val database: RecipeDatabase): RecipeRepository {

    private val recipeDao = database.recipeDao()

    private fun isDataStale(recipe:Recipe): Boolean{
        return try {
            val shouldRefresh = recipe.timeStamp?.let { timeStamp ->
                val diff = Date().time - timeStamp.toLong()
                Log.i("RecipeRepositoryImpl", "isDataStale: $diff")
                diff > 1000 * 60 * 60
            }
            shouldRefresh ?: true
        }catch (e:Exception){
            true
        }
    }

    override fun getRandomRecipes(tag: String)= networkBoundResource(
        query = { recipeDao.getRecipes(tag).map { recipes -> recipes.map { recipe -> recipe.toRecipe() } } },
        fetch = { api.getRandomRecipes(tags = tag) },
        saveFetchResult = { recipes ->
            val recipeList = recipes.recipes.map { recipe ->
                recipe.toRecipeEntity().copy(tag = tag, timeStamp = Date().time.toString())
            }
            database.withTransaction {
                recipeDao.deleteRecipes(tag)
                recipeDao.insertRecipes(recipeList)
            }
        },
        shouldFetch = { recipes ->
            if (recipes.isEmpty()){
                true
            }else{
                isDataStale(recipes[0])
            }
        }
    )
    override fun getRecipeById(id: Int) = networkBoundResource(
        query = { recipeDao.getRecipe(id).map {recipes ->
                if (recipes.isEmpty()) {
                    null
                }else{
                    recipes[0].toRecipe()
                }
            }
        },
        fetch = { api.getRecipe(id) },
        saveFetchResult = { recipe ->
            val recipeEntity = recipe.toRecipeEntity().copy(timeStamp = Date().time.toString())
            database.withTransaction {
                recipeDao.deleteRecipe(id)
                recipeDao.insertRecipe(recipeEntity)
            }
        },
        shouldFetch = { recipe ->
            if (recipe == null){
                true
            }else{
                isDataStale(recipe)
            }
        }
    )

    override suspend fun updateRecipe(recipe: Recipe) {
//        recipeDao.updateRecipe(recipe.t)
    }
}