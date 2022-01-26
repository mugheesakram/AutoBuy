package com.technology.autobuy.ui.cartypes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.technology.autobuy.data.base.ApiError
import com.technology.autobuy.data.base.ApiResponse
import com.technology.autobuy.data.remote.CarTypeApi
import com.technology.autobuy.utils.CoroutineRule
import com.technology.autobuy.utils.base.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CarTypeVMTest {
    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var sut: CarTypeVM

    @After
    fun cleanUp() {
        clearAllMocks()
    }

    @Test
    fun `When CarModel API is Successful`() {
        //Given
        val carApi = mockk<CarTypeApi> {
            coEvery { getCarsOfManufacturer("manufacturerId") } returns ApiResponse.Success(
                200,
                mockk {
                    every { carTypes } returns mapOf("F" to "Ford", "H" to "Honda")
                })
        }
        //When
        sut = CarTypeVM(carApi)
        sut.getModelsForManufacturer("manufacturerId")
        //Then
        Assert.assertEquals(2, sut.carTypes.value?.size)
        Assert.assertEquals("Ford", sut.carTypes.value?.get(0)?.name)
    }

    @Test
    fun `When CarModel API gives error`() {
        //Given
        val carApi = mockk<CarTypeApi> {
            coEvery { getCarsOfManufacturer("manufacturerId") } returns ApiResponse.Error(
                ApiError(400, "Message")
            )
        }
        //When
        sut = CarTypeVM(carApi)
        sut.getModelsForManufacturer("manufacturerId")
        //Then
        Assert.assertEquals(0, sut.carTypes.value?.size)
        Assert.assertEquals(UIState.Error("Message"), sut.uiState.value)
    }

    @Test
    fun `getFilteredData returns valid data`() {
        //Given
        val carApi = mockk<CarTypeApi> {
            coEvery { getCarsOfManufacturer("manufacturerId") } returns ApiResponse.Success(
                200,
                mockk {
                    every { carTypes } returns mapOf("F" to "Ford", "H" to "Honda")
                })
        }
        //When
        sut = CarTypeVM(carApi)
        sut.getModelsForManufacturer("manufacturerId") // Get Data in initial List
        sut.getFilteredData("Ford")
        //Then
        Assert.assertEquals("F", sut.carTypes.value?.first()?.id)
    }
}