package com.robert.nganga.recipeapp.feature_recipe.domain.use_case

import com.robert.nganga.recipeapp.core.util.Resource
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomRecipes@Inject constructor(
    private val repository: RecipeRepository) {

    operator fun invoke(tag: String): Flow<Resource<List<Recipe>>> {
        val newTag = if (tag == "all") "" else tag
        return repository.getRandomRecipes(newTag)
    }
}
