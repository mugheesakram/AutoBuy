package com.technology.autobuy.ui.home

import androidx.activity.viewModels
import com.technology.autobuy.R
import com.technology.autobuy.databinding.ActivityHomeBinding
import com.technology.autobuy.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, IHome.ViewModel>(),
    IHome.View {
    override val navHostId: Int = R.id.nav_host_fragment
    override val navigationGraphId = R.navigation.nav_home_graph
    override val viewModel by viewModels<HomeVM>()
    override fun getLayoutId() = R.layout.activity_home
    override fun getViewBinding() = ActivityHomeBinding.inflate(layoutInflater)
}