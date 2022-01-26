package com.technology.autobuy.ui.cartypedates

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.utils.base.IBase
import com.technology.autobuy.utils.base.UIState

interface ICarTypeDate {
    interface View : IBase.View<ViewModel> {
        fun viewModelObservers()
        fun showErrorView()
        fun showLoadingView()
        fun showDataView()
        fun setCarModeList(list: MutableList<ManufacturerItem>)
    }

    interface ViewModel : IBase.ViewModel {
        var manufacturerItem: ManufacturerItem?
        var carModel: ManufacturerItem?
        val modelsByYear: MutableLiveData<MutableList<ManufacturerItem>>
        var uiState: LiveData<UIState>
        fun getCarByDate(manufacturerId: String, modelName: String)
        fun setBundleValues(bundle: Bundle)
        fun refreshData()
    }
}