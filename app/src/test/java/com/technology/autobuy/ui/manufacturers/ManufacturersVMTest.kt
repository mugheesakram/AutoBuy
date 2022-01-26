package com.technology.autobuy.ui.manufacturers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.data.remote.CarTypeApi
import com.technology.autobuy.utils.CoroutineRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ManufacturersVMTest {
    @get:Rule
    var mainCoroutineRule = CoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var sut: ManufacturersVM

    @Before
    fun cleanUp() {
        clearAllMocks()
    }

    @Test
    fun `when getManufacturer is successful`() {
        val carTypeApi = mockk<CarTypeApi> {
            coEvery { getManufacturers() } returns flowOf(
                PagingData.from(
                    listOf(
                        ManufacturerItem("M", "Ford"),
                        ManufacturerItem("A", "Audi")
                    )
                )
            )
        }

        sut = ManufacturersVM(carTypeApi)

        sut.getManufacturers()

        Assert.assertEquals("Ford",sut.manufacturerFlow.d
    }
}