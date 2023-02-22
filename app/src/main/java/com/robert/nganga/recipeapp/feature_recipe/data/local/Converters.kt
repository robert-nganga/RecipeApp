package com.robert.nganga.recipeapp.feature_recipe.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.robert.nganga.recipeapp.feature_recipe.data.util.JsonParser
import com.robert.nganga.recipeapp.feature_recipe.domain.model.AnalyzedInstruction


@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromAnalyzedInstructionsJson(value: String): List<AnalyzedInstruction>{
        val listType = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return jsonParser.fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun analyzedInstructionsToJson(list: List<AnalyzedInstruction>): String{
        val listType = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return jsonParser.toJson(list, listType) ?: ""
    }

    @TypeConverter
    fun fromExtendedIngredientsJson(value: String): List<AnalyzedInstruction>{
        val listType = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return jsonParser.fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun extendedIngredientsToJson(list: List<AnalyzedInstruction>): String{
        val listType = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return jsonParser.toJson(list, listType) ?: ""
    }
}
