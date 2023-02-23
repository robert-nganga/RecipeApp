package com.robert.nganga.recipeapp.feature_recipe.data.repository

import androidx.room.withTransaction
import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.data.local.RecipeDatabase
import com.robert.nganga.recipeapp.feature_recipe.data.remote.RecipeApi
import com.robert.nganga.recipeapp.feature_recipe.data.util.networkBoundResource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class RecipeRepositoryImpl(
    private val api: RecipeApi,
    private val database: RecipeDatabase): RecipeRepository {

    private val recipeDao = database.recipeDao()

    override fun getRandomRecipes(tag: String)= networkBoundResource(
        query = {
            recipeDao.getRecipes(tag).map { recipes ->
                recipes.map { recipe ->
                    recipe.toRecipe()
                }
            }
        },
        fetch = { api.getRandomRecipes(tags = tag) },
        saveFetchResult = { recipes ->
            val recipeList = recipes.recipes.map { recipe ->
                recipe.toRecipeEntity().copy(tag = tag)
            }

            database.withTransaction {
                recipeDao.deleteRecipes(tag)
                recipeDao.insertRecipes(recipeList)
            }
        }
    )
    override fun getRecipeById(id: Int) = networkBoundResource(
        query = {
            recipeDao.getRecipe(id).map { recipe ->
                recipe.toRecipe()
            }
        },
        fetch = { api.getRecipe(id) },
        saveFetchResult = { recipe ->
            val recipeEntity = recipe.toRecipeEntity()
            database.withTransaction {
                recipeDao.deleteRecipe(id)
                recipeDao.insertRecipe(recipeEntity)
            }
        }
    )
}