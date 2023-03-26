package com.robert.nganga.recipeapp.feature_recipe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.robert.nganga.recipeapp.feature_recipe.domain.model.AnalyzedInstruction
import com.robert.nganga.recipeapp.feature_recipe.domain.model.ExtendedIngredient
import com.robert.nganga.recipeapp.feature_recipe.domain.model.Recipe


@Entity(tableName = "recipe_table")
data class RecipeEntity(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cookingMinutes: Int,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val glutenFree: Boolean,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String?,
    val imageType: String?,
    val instructions: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val tag: String?,
    val timeStamp: String?,
    val isFavorite: Boolean
){
    fun toRecipe() = Recipe(
        aggregateLikes = aggregateLikes,
        analyzedInstructions = analyzedInstructions,
        cookingMinutes = cookingMinutes,
        cuisines = cuisines,
        dairyFree = dairyFree,
        diets = diets,
        dishTypes = dishTypes,
        extendedIngredients = extendedIngredients,
        glutenFree = glutenFree,
        id = id,
        image = image,
        imageType = imageType,
        instructions = instructions,
        readyInMinutes = readyInMinutes,
        servings = servings,
        sourceName = sourceName,
        sourceUrl = sourceUrl,
        summary = summary,
        title = title,
        vegan = vegan,
        vegetarian = vegetarian,
        timeStamp = timeStamp,
        tag = tag,
        isFavorite = isFavorite
    )
}
