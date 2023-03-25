package com.robert.nganga.recipeapp.data.remote

import com.google.common.base.Charsets
import com.google.common.truth.Truth.assertThat
import com.robert.nganga.recipeapp.feature_recipe.data.remote.RecipeApi
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
            assertThat(recipe.id).isEqualTo(716429)
            assertThat(recipe.title).isEqualTo("Spicy Sausage and Pepper Pasta")
            assertThat(recipe.image).isEqualTo("https://spoonacular.com/recipeImages/716429-312x231.jpg")
            assertThat(recipe.imageType).isEqualTo("jpg")
            assertThat(recipe.readyInMinutes).isEqualTo(30)
            assertThat(recipe.servings).isEqualTo(4)
            assertThat(recipe.sourceUrl).isEqualTo("http://www.food.com/recipe/spicy-sausage-and-pepper-pasta-716429")
            assertThat(recipe.sourceName).isEqualTo("Food.com")
            assertThat(recipe.spoonacularSourceUrl).isEqualTo("https://spoonacular.com/spicy-sausage-and-pepper-pasta-716429")
        }

    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}