package com.technology.autobuy.utils.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, V : IBase.ViewModel> : IBase.View<V>,
    AppCompatActivity() {
    lateinit var binding: VB
    private var navHostFragment: NavHostFragment? = null
    abstract val navHostId: Int

    @get:NavigationRes
    abstract val navigationGraphId: Int

    @LayoutRes
    abstract fun getLayoutId(): Int
    protected abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerStateListeners()
        binding = getViewBinding()
        setContentView(binding.root)
        initNavigationGraph()
    }

    override fun onDestroy() {
        unregisterStateListeners()
        super.onDestroy()
    }

    private fun initNavigationGraph() {
        try {
            navHostFragment = supportFragmentManager.findFragmentById(navHostId) as NavHostFragment?
            navHostFragment?.navController?.apply {
                graph = navInflater.inflate(navigationGraphId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e.message)
        }
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
}
