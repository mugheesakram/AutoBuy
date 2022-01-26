package com.technology.autobuy.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.technology.autobuy.data.base.BaseRepository
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.data.paging.ManufacturerPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarTypeRepository @Inject constructor(service: CarTypeService) : BaseRepository(),
    CarTypeApi {
    private val api = service
    override suspend fun getManufacturers():
            Flow<PagingData<ManufacturerItem>> = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { ManufacturerPagingDataSource(api) }
    ).flow

    override suspend fun getCarsOfManufacturer(manufacturer: String) = executeSafely(
        call = {
            api.getCarsOfManufacturer(manufacturer)
        }
    )

    override suspend fun getCarTypeBuiltDates(manufacturer: String, mainType: String) =
        executeSafely(
            call = {
                api.getCarTypeBuiltDates(manufacturer, mainType)
            }
        )
}