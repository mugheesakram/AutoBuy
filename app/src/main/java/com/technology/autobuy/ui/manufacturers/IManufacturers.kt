package com.technology.autobuy.ui.manufacturers

import androidx.paging.PagingData
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.utils.base.IBase
import com.technology.autobuy.utils.interfaces.ItemClickListener
import kotlinx.coroutines.flow.Flow

interface IManufacturers {
    interface View : IBase.View<ViewModel> {
        val itemClickListener: ItemClickListener
    }

    interface ViewModel : IBase.ViewModel {
        val manufacturerFlow: Flow<PagingData<ManufacturerItem>>
        fun getManufacturers()
    }
}