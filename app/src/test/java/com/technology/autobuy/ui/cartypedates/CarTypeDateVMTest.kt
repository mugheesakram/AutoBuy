package com.technology.autobuy.ui.cartypedates

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
internal class CarTypeDateVMTest {
    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var sut: CarTypeDateVM

    @After
    fun clearAll() {
        clearAllMocks()
    }

    @Test
    fun `When ModelByYear API is successful`() {
        //Given
        val carApi = mockk<CarTypeApi> {
            coEvery { getCarTypeBuiltDates("manufacturerId", "model") } returns ApiResponse.Success(
                200,
                mockk {
                    every { carTypesDates } returns mapOf("1996" to "1996", "1995" to "1995")
                })
        }
        //When
        sut = CarTypeDateVM(carApi)
        sut.getCarByDate("manufacturerId", "model")
        //Then
        Assert.assertEquals(2, sut.modelsByYear.value?.size)
        Assert.assertEquals("1996", sut.modelsByYear.value?.get(0)?.name)
    }

    @Test
    fun `When ModelByYear API gives error`() {
        //Given
        val carApi = mockk<CarTypeApi> {
            coEvery { getCarTypeBuiltDates("manufacturerId", "model") } returns ApiResponse.Error(
                ApiError(
                    400,
                    "Message"
                )
            )
        }
        //When
        sut = CarTypeDateVM(carApi)
        sut.getCarByDate("manufacturerId", "model")
        //Then
        Assert.assertEquals(0, sut.modelsByYear.value?.size)
        Assert.assertEquals(UIState.Error("Message"), sut.uiState.value)
    }
}