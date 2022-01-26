package com.technology.autobuy.ui.cartypes

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.technology.autobuy.data.base.ApiResponse
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.data.remote.CarTypeApi
import com.technology.autobuy.utils.MANUFACTURER_ITEM_KEY
import com.technology.autobuy.utils.base.BaseViewModel
import com.technology.autobuy.utils.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarTypeVM @Inject constructor(
    private var carTypeApi: CarTypeApi
) : BaseViewModel(), ICarType.ViewModel {

    private var _carTypes: MutableLiveData<MutableList<ManufacturerItem>> = MutableLiveData()
    private var _initialList: MutableList<ManufacturerItem>? = mutableListOf()

    override val carTypes: LiveData<MutableList<ManufacturerItem>>
        get() = _carTypes
    override var manufacturerItem: ManufacturerItem? = null
    private val _uiState: MutableLiveData<UIState> = MutableLiveData()
    override var uiState: LiveData<UIState> = _uiState

    override fun onCreate() {
        super.onCreate()
        manufacturerItem?.id?.let { getModelsForManufacturer(it) }
    }


    override fun getModelsForManufacturer(manufacturerId: String) {
        launch {
            _uiState.postValue(UIState.Loading)
            when (val response = carTypeApi.getCarsOfManufacturer(manufacturer = manufacturerId)) {
                is ApiResponse.Success -> {
                    _initialList = response.data.carTypes?.toList()?.map {
                        ManufacturerItem(it.first, it.second)
                    }?.toMutableList()
                    _carTypes.postValue(_initialList)
                }
                is ApiResponse.Error -> {
                    _carTypes.value = mutableListOf()
                    _uiState.postValue(UIState.Error(response.error.message))
                }
            }
        }
    }

    override fun setBundleValues(bundle: Bundle) {
        if (!bundle.isEmpty)
            manufacturerItem = bundle.getParcelable(MANUFACTURER_ITEM_KEY)
    }

    override fun getFilteredData(search: String) {
        _carTypes.value = if (search.isNotBlank())
            _initialList?.filter {
                it.name?.lowercase()?.contains(search.lowercase()) == true
            } as MutableList<ManufacturerItem>?
        else _initialList
    }

    override fun refreshData() {
        manufacturerItem?.id?.let { getModelsForManufacturer(it) }
    }
}