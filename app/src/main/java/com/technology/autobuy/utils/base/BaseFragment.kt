package com.technology.autobuy.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, V : IBase.ViewModel> : IBase.View<V>,
    Fragment() {
    lateinit var binding: VB

    @LayoutRes
    abstract fun getLayoutId(): Int
    protected abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerStateListeners()
        binding = getViewBinding()
        return binding.root
    }

    private fun registerStateListeners() {
        if (viewModel is BaseViewModel) {
            viewModel.registerLifecycleOwner(this)
        }
    }

    private fun unregisterStateListeners() {
        if (viewModel is BaseViewModel) {
            viewModel.unregisterLifecycleOwner(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterStateListeners()
    }
}