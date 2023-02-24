package com.robert.nganga.recipeapp.feature_recipe.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.robert.nganga.recipeapp.feature_recipe.data.util.JsonParser
import com.robert.nganga.recipeapp.feature_recipe.domain.model.AnalyzedInstruction
import com.robert.nganga.recipeapp.feature_recipe.domain.model.ExtendedIngredient


@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromAnalyzedInstructionsJson(value: String): List<AnalyzedInstruction>{
        val listType = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return jsonParser.fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun toAnalyzedInstructionsToJson(list: List<AnalyzedInstruction>): String{
        val listType = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return jsonParser.toJson(list, listType) ?: ""
    }

    @TypeConverter
    fun fromExtendedIngredientsJson(value: String): List<ExtendedIngredient>{
        val listType = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return jsonParser.fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun toExtendedIngredientsToJson(list: List<ExtendedIngredient>): String{
        val listType = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return jsonParser.toJson(list, listType) ?: ""
    }

    // convert list of strings to json
    @TypeConverter
    fun fromStringListToJson(value: List<String>): String{
        val listType = object : TypeToken<List<String>>() {}.type
        return jsonParser.toJson(value, listType) ?: ""
    }

    // convert json to list of strings
    @TypeConverter
    fun fromJsonToStringList(value: String): List<String>{
        val listType = object : TypeToken<List<String>>() {}.type
        return jsonParser.fromJson(value, listType) ?: emptyList()
    }
}
