package com.technology.autobuy.data.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.data.models.ManufacturersInfo
import com.technology.autobuy.data.remote.CarTypeService
import com.technology.autobuy.utils.CoroutineRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class ManufacturerPagingDataSourceTest {
    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val firstResponse = Response.success(ManufacturersInfo("1", "1", "3", mapOf("H" to "Honda")))
    val secondResponse = Response.success(ManufacturersInfo("2", "1", "3", mapOf("F" to "Ford")))


    lateinit var pagingSource: ManufacturerPagingDataSource

    @After
    fun cleanUp() {
        clearAllMocks()
    }

    @Test
    fun `PageSource gets an error`() = runBlocking {
        //Given
        val error = RuntimeException("400", Throwable())
        val carTypeService = mockk<CarTypeService>() {
            coEvery { getManufacturers("0", "15") } throws error
        }
        val expectedResult = PagingSource.LoadResult.Error<Int, ManufacturerItem>(error)
        //When
        pagingSource = ManufacturerPagingDataSource(carTypeService)
        //Then
        Assert.assertEquals(
            expectedResult,
            pagingSource.load(PagingSource.LoadParams.Refresh(0, 1, false))
        )
    }

    @Test
    fun `PageSource makes successful API call`() = runBlocking {
        val carTypeService = mockk<CarTypeService>() {
            coEvery { getManufacturers("0", "15") } returns firstResponse
        }
        //When
        pagingSource = ManufacturerPagingDataSource(carTypeService)
        val expectedResult = PagingSource.LoadResult.Page(
            data = firstResponse.body()?.manufacturerInfo?.toList()
                ?.map { ManufacturerItem(it.first, it.second) } as List<ManufacturerItem>,
            prevKey = null,
            nextKey = 1
        )
        //Then
        Assert.assertEquals(
            expectedResult,
            pagingSource.load(PagingSource.LoadParams.Refresh(0, 1, false))
        )
    }

    @Test
    fun `PageSource makes successful append`() = runBlocking {
        val carTypeService = mockk<CarTypeService>() {
            coEvery { getManufacturers(any(), any()) } returns secondResponse
        }
        //When
        pagingSource = ManufacturerPagingDataSource(carTypeService)
        val expectedResult = PagingSource.LoadResult.Page(
            data = secondResponse.body()?.manufacturerInfo?.toList()
                ?.map { ManufacturerItem(it.first, it.second) } as List<ManufacturerItem>,
            prevKey = null,
            nextKey = 2
        )
        //Then
        Assert.assertEquals(
            expectedResult,
            pagingSource.load(PagingSource.LoadParams.Refresh(1, 1, false))
        )
    }
}