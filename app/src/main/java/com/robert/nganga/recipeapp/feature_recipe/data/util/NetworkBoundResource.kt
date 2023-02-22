package com.robert.nganga.recipeapp.feature_recipe.data.util


import android.util.Log
import com.robert.nganga.recipeapp.core.util.Resource
import kotlinx.coroutines.flow.*
import okio.IOException
import retrofit2.HttpException

inline fun <ResultType, RequestType>networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.loading(data))
        try {
            saveFetchResult(fetch())
            query().map { Resource.success(it) }
        } catch (e: HttpException) {
            Log.e("NetworkBoundResource", "An error happened: $e", e)
            query().map { Resource.error(e.message ?: "Unknown error", it) }
        } catch (e: IOException) {
            Log.e("NetworkBoundResource", "An error happened: $e", e)
            query().map { Resource.error("Network error has occurred", it) }
        }
    } else {
        query().map { Resource.success(it) }
    }

    emitAll(flow)
}
