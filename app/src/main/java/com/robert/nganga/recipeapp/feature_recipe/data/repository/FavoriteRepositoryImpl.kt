package com.robert.nganga.recipeapp.feature_recipe.data.repository

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.data.local.RecipeDatabase
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Favorite
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl@Inject constructor(
    private val database: RecipeDatabase): FavoriteRepository{

    private val favoriteDao = database.favoriteDao()


    override fun getFavoriteRecipes(): Flow<Resource<List<Favorite>>> {
        return favoriteDao.getAllFavorites().map { recipes->
            Resource.success(data = recipes.map { it.toFavorite() })
        }
    }

    override fun getFavoriteRecipe(id: Int): Flow<Resource<Favorite?>> {
        return favoriteDao.getFavorite(id).map { recipe->
            Resource.success(data = recipe[0].toFavorite())
        }
    }

    override suspend fun insertFavoriteRecipe(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite.toFavoriteEntity())
    }

    override suspend fun deleteFavoriteRecipe(favorite: Favorite) {
        favoriteDao.deleteFavorite(favorite.toFavoriteEntity())
    }

    override suspend fun deleteAllFavoriteRecipes() {
        favoriteDao.deleteAllFavorites()
    }

}