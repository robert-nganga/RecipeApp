package com.robert.nganga.recipeapp.feature_recipe.data.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.data.local.RecipeDatabase
import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.RecipeEntity
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Favorite
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl@Inject constructor(
        database: RecipeDatabase): FavoriteRepository {

    private val favoriteDao = database.favoriteDao()


    override fun getFavoriteRecipes(): Flow<Resource<List<Recipe>>> {
        return favoriteDao.getAllFavorites().map { favorites->
            Resource.success(data = favorites.map { favorite->
                favorite.toRecipe()
            })
        }
    }

    override fun getFavoriteRecipeById(id: Int): Flow<Resource<Recipe?>> {
        return favoriteDao.getFavoriteById(id).map { favorite->
            Resource.success(data = favorite[0].toRecipe())
        }
    }

    override suspend fun addFavorite(favorite: Recipe) {
       favoriteDao.addFavorite(favorite.toRecipeEntity())
    }

    override suspend fun removeFavorite(favorite: Recipe) {
        favoriteDao.removeFavorite(favorite.toRecipeEntity())
    }

    override suspend fun removeAllFavorite(favorites: List<Recipe>) {
        favoriteDao.removeAllFavorites(favorites.map { favorite->
            favorite.toRecipeEntity()
        })
    }

}