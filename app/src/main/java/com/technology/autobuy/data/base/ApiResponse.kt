package com.technology.autobuy.data.base


sealed class ApiResponse<out T : BaseResponse> {
    data class Success<out T : BaseResponse>(val code: Int, val data: T) : ApiResponse<T>()
    data class Error(val error: ApiError) : ApiResponse<Nothing>()
}