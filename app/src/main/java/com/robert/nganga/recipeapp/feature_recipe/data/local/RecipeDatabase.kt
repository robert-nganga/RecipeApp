package com.robert.nganga.recipeapp.feature_recipe.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.robert.nganga.recipeapp.feature_recipe.data.local.entity.RecipeEntity


@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun recipeDao(): RecipeEntityDao
}