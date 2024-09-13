package com.chaaba.library


sealed class ResourceUiState<out T> {
    data class Success<T>(val data: T) : ResourceUiState<T>()
    data class Error(val message: String? = null, val throwable: Throwable? = null) : ResourceUiState<Nothing>()
    data object Loading : ResourceUiState<Nothing>()
    data object Empty : ResourceUiState<Nothing>()
}


sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure(val message: String?, val throwable: Throwable?) : Result<Nothing>()
}

inline fun <reified I, reified O> Result<I>.map(mapSuccess: (I) -> O): Result<O> {
    return when (this) {
        is Result.Success -> Result.Success(
            mapSuccess(value)
        )

        is Result.Failure -> this
    }
}
