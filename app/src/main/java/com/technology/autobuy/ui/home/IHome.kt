package com.technology.autobuy.ui.home

import com.technology.autobuy.utils.base.IBase

interface IHome {
    interface View : IBase.View<ViewModel> {
    }

    interface ViewModel : IBase.ViewModel {
    }
}