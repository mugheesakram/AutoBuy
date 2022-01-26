package com.technology.autobuy.ui.cartypedates

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.technology.autobuy.data.base.ApiResponse
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.data.remote.CarTypeApi
import com.technology.autobuy.utils.CAR_MODEL_ITEM
import com.technology.autobuy.utils.MANUFACTURER_ITEM_KEY
import com.technology.autobuy.utils.base.BaseViewModel
import com.technology.autobuy.utils.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarTypeDateVM @Inject constructor(
    private val carTypeApi: CarTypeApi
) : BaseViewModel(), ICarTypeDate.ViewModel {
    private val _modelsByYear: MutableLiveData<MutableList<ManufacturerItem>> =
        MutableLiveData()
    override val modelsByYear: MutableLiveData<MutableList<ManufacturerItem>>
        get() = _modelsByYear

    override var manufacturerItem: ManufacturerItem? = null
    override var carModel: ManufacturerItem? = null
    private val _uiState: MutableLiveData<UIState> = MutableLiveData()
    override var uiState: LiveData<UIState> = _uiState

    override fun onCreate() {
        super.onCreate()
        getCarByDate(
            manufacturerId = manufacturerItem?.id ?: "", carModel?.id ?: ""
        )
    }

    override fun getCarByDate(manufacturerId: String, modelName: String) {
        launch {
            _uiState.postValue(UIState.Loading)
            when (val response = carTypeApi.getCarTypeBuiltDates(manufacturerId, modelName)) {
                is ApiResponse.Success -> {
                    _modelsByYear.postValue(
                        response.data.carTypesDates?.toList()
                            ?.map { ManufacturerItem(it.first, it.second) }?.toMutableList()
                    )
                }
                is ApiResponse.Error -> {
                    _modelsByYear.value = mutableListOf()
                    _uiState.postValue(UIState.Error(response.error.message))
                }
            }
        }
    }

    override fun setBundleValues(bundle: Bundle) {
        if (!bundle.isEmpty) {
            manufacturerItem = bundle.getParcelable(MANUFACTURER_ITEM_KEY)
            carModel = bundle.getParcelable(CAR_MODEL_ITEM)
        }
    }

    override fun refreshData() {
        getCarByDate(manufacturerItem?.id ?: "", carModel?.id ?: "")
    }
}