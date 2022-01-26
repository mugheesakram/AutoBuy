package com.technology.autobuy.utils.base

interface IBase {
    interface View<V : ViewModel> {
        val viewModel: V
    }

    interface ViewModel : ILifecycle {
    }
}

sealed class UIState {
    object Loading : UIState()
    data class Error(val error: String? = null) : UIState()
}
