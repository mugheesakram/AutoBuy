package com.technology.autobuy.ui.cartypes

import android.os.Bundle
import androidx.lifecycle.LiveData
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.utils.base.IBase
import com.technology.autobuy.utils.base.UIState
import com.technology.autobuy.utils.interfaces.ItemClickListener

interface ICarType {
    interface View : IBase.View<ViewModel> {
        val listItemClickListener: ItemClickListener
        fun setObservers()
        fun showErrorView()
        fun showLoadingView()
        fun showDataView()
        fun setCarModeList(list: MutableList<ManufacturerItem>)
    }

    interface ViewModel : IBase.ViewModel {
        var manufacturerItem: ManufacturerItem?
        val carTypes: LiveData<MutableList<ManufacturerItem>>
        var uiState: LiveData<UIState>
        fun setBundleValues(bundle: Bundle)
        fun getModelsForManufacturer(manufacturerId: String)
        fun getFilteredData(search: String)
        fun refreshData()
    }
}