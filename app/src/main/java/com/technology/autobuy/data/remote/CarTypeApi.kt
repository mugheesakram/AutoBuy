package com.technology.autobuy.data.remote

import androidx.paging.PagingData
import com.technology.autobuy.data.base.ApiResponse
import com.technology.autobuy.data.models.CarTypeInfo
import com.technology.autobuy.data.models.ManufacturerCarTypes
import com.technology.autobuy.data.models.ManufacturerItem
import kotlinx.coroutines.flow.Flow

interface CarTypeApi {
    suspend fun getManufacturers(): Flow<PagingData<ManufacturerItem>>

    suspend fun getCarsOfManufacturer(manufacturer: String): ApiResponse<ManufacturerCarTypes>
    suspend fun getCarTypeBuiltDates(
        manufacturer: String,
        mainType: String
    ): ApiResponse<CarTypeInfo>
}