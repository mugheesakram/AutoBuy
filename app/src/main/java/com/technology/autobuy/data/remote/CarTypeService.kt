package com.technology.autobuy.data.remote

import com.technology.autobuy.data.models.CarTypeInfo
import com.technology.autobuy.data.models.ManufacturerCarTypes
import com.technology.autobuy.data.models.ManufacturersInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CarTypeService {
    @GET("car-types/manufacturer")
    suspend fun getManufacturers(
        @Query("page") page: String,
        @Query("pageSize") pageSize: String
    ): Response<ManufacturersInfo>

    @GET("car-types/main-types")
    suspend fun getCarsOfManufacturer(@Query("manufacturer") manufacturer: String): Response<ManufacturerCarTypes>

    @GET("car-types/built-dates")
    suspend fun getCarTypeBuiltDates(
        @Query("manufacturer") manufacturer: String,
        @Query("main-type") mainType: String,
    ): Response<CarTypeInfo>
}