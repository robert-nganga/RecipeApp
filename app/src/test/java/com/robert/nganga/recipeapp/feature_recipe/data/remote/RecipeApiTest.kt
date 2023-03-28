package com.robert.nganga.recipeapp.feature_recipe.data.remote

import com.google.common.base.Charsets
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeApiTest {
    private lateinit var service: RecipeApi
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }

    private fun enqueueMockResponse(fileName: String){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getRandomRecipes_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("randomrecipes.json")
            val response = service.getRandomRecipes(20)
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/random?number=20&apiKey=f93c39d6997b453f8321b539332a9ca8&tags=")
        }
    }

    @Test
    fun getRandomRecipes_receivedResponse_correctNumber(){
        runBlocking {
            enqueueMockResponse("randomrecipes.json")
            val response = service.getRandomRecipes(20)
            assertThat(response.recipes.size).isEqualTo(20)
        }
    }

    @Test
    fun getRandomRecipes_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("randomrecipes.json")
            val response = service.getRandomRecipes(20)
            val recipe = response.recipes[0]
            assertThat(recipe.id).isEqualTo(715515)
            assertThat(recipe.title).isEqualTo("Southern Style Green Bean")
        }
    }

    @Test
    fun getRecipe_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("recipe_info.json")
            val response = service.getRecipe(716429)
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/716429/information?apiKey=f93c39d6997b453f8321b539332a9ca8")
        }
    }

    @Test
    fun getRecipe_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("recipe_info.json")
            val response = service.getRecipe(716429)
            assertThat(response.id).isEqualTo(716429)
            assertThat(response.title).isEqualTo("Pasta with Garlic, Scallions, Cauliflower & Breadcrumbs")
        }
    }

    @Test
    fun searchByIngredients_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("search_recipe.json")
            val response = service.searchByIngredients(ingredients = "apples,flour,sugar")
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(response.size).isEqualTo(20)
            assertThat(request.path).isEqualTo("/findByIngredients?apiKey=f93c39d6997b453f8321b539332a9ca8&number=20&ingredients=apples%2Cflour%2Csugar")
        }
    }

    @Test
    fun searchByIngredients_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("search_recipe.json")
            val response = service.searchByIngredients(ingredients = "apples,flour,sugar")
            val recipe = response[0]
            assertThat(recipe.id).isEqualTo(632583)
            assertThat(recipe.title).isEqualTo("Apple Pie with PB&J Streusel")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}