package id.rosyid.moviecatalogue.data.remote

import id.rosyid.moviecatalogue.utils.EspressoIdlingResource
import id.rosyid.moviecatalogue.utils.Resource
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            EspressoIdlingResource.increment()
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    EspressoIdlingResource.decrement()
                    return Resource.success(body)
                }
            }
            return error("${response.code()} >> ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> =
        Resource.error("Network call has failed for a following reason: $message")
}
