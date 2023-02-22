package com.robert.nganga.recipeapp.feature_recipe.data.repository

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


    override fun getRecipesByTag(tag: String)= networkBoundResource(
        query = {
            recipeDao.getRecipes(tag).map { recipes ->
                recipes.map { recipe ->
                    recipe.toRecipe()
                }
            }
        },
        fetch = {
            api.getRandomRecipes(tags = tag)
        },
        saveFetchResult = { recipes ->
            val recipeList = recipes.recipes.map { recipe ->
                recipe.toRecipeEntity().copy(timeStamp= Date().toString(), tag = tag)
            }
        },
        shouldFetch = { recipes ->
            recipes.isEmpty()
        }
    )
    override fun getRecipeById(id: Int): Flow<Resource<Recipe>> {
        TODO("Not yet implemented")
    }

}