package com.technology.autobuy.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.data.remote.CarTypeService
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class ManufacturerPagingDataSource @Inject constructor(private var api: CarTypeService) :
    PagingSource<Int, ManufacturerItem>() {
    override fun getRefreshKey(state: PagingState<Int, ManufacturerItem>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ManufacturerItem> {
        val pageNumber = params.key ?: 1 // Page Number
        return try {
            // Calling the API
            val response = api.getManufacturers(pageNumber.toString(), "15")
            val responseData = mutableListOf<ManufacturerItem>()
            // if the call was successful then transform the Map into List of Item Objects.
            val data = response.body()?.manufacturerInfo?.toList()
                ?.map { ManufacturerItem(it.first, it.second) }
            data?.let { responseData.addAll(it) }
            // Checking if the nextPage is available according to APIs totalPageCount or not.
            // If the current pageNumber is less than totalPageCount then we will call APi the next time
            // if not then we will not call API
            val nextPageNumber: Int? =
                if (response.body()?.totalPageCount?.toInt() != pageNumber) {
                    pageNumber.plus(1)
                } else null
            // Loading the data with latest information.
            LoadResult.Page(
                data = responseData,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        } catch (exception: UnknownHostException) {
            return LoadResult.Error(exception)
        }
    }
}