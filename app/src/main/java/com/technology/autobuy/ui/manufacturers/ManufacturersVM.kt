package com.technology.autobuy.ui.manufacturers

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.data.remote.CarTypeApi
import com.technology.autobuy.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class ManufacturersVM @Inject constructor(
    var carTypeApi: CarTypeApi
) : BaseViewModel(), IManufacturers.ViewModel {
    private var _manufacturerFlow: Flow<PagingData<ManufacturerItem>> = emptyFlow()
    override val manufacturerFlow: Flow<PagingData<ManufacturerItem>>
        get() = _manufacturerFlow

    override fun onCreate() {
        super.onCreate()
        getManufacturers()
        manufacturerFlow.asLiveData().value
    }

    override fun getManufacturers() =
        launchPagingAsync({ carTypeApi.getManufacturers().cachedIn(viewModelScope) }, {
            _manufacturerFlow = it
        })
}