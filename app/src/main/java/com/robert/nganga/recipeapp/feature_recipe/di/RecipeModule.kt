package com.robert.nganga.recipeapp.feature_recipe.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.robert.nganga.recipeapp.feature_recipe.data.local.Converters
import com.robert.nganga.recipeapp.feature_recipe.data.local.RecipeDatabase
import com.robert.nganga.recipeapp.feature_recipe.data.remote.RecipeApi
import com.robert.nganga.recipeapp.feature_recipe.data.remote.RecipeApi.Companion.BASE_URL
import com.robert.nganga.recipeapp.feature_recipe.data.repository.FavoriteRepositoryImpl
import com.robert.nganga.recipeapp.feature_recipe.data.repository.RecipeRepositoryImpl
import com.robert.nganga.recipeapp.feature_recipe.data.repository.SearchByIngredientsRepositoryImpl
import com.robert.nganga.recipeapp.feature_recipe.data.repository.SearchRepositoryImpl
import com.robert.nganga.recipeapp.feature_recipe.data.util.GsonParser
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.FavoriteRepository
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.RecipeRepository
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchByIngredientsRepository
import com.robert.nganga.recipeapp.feature_recipe.domain.repository.SearchRepository
import com.robert.nganga.recipeapp.feature_recipe.domain.use_case.GetRandomRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {

    @Provides
    @Singleton
    fun provideGetRandomRecipes(
        recipeService: RecipeRepository
    ): GetRandomRecipes {
        return GetRandomRecipes(recipeService)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(
        database: RecipeDatabase,
        api: RecipeApi
    ): RecipeRepository {
        return RecipeRepositoryImpl(api, database)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        database: RecipeDatabase,
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun provideSearchByIngredientRepository(
        api: RecipeApi
    ): SearchByIngredientsRepository {
        return SearchByIngredientsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        api: RecipeApi
    ): SearchRepository {
        return SearchRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRecipeDatabase(app : Application): RecipeDatabase{
        return Room.databaseBuilder(app,
            RecipeDatabase::class.java,
            "recipe_db").addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesApi(): RecipeApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RecipeApi::class.java)
    }

}