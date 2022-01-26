package com.technology.autobuy.ui.manufacturers

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.technology.autobuy.R
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.databinding.FragmentManufacturersBinding
import com.technology.autobuy.ui.adapters.ManufacturerPagingAdapter
import com.technology.autobuy.ui.adapters.PagingLoadStateAdapter
import com.technology.autobuy.utils.MANUFACTURER_ITEM_KEY
import com.technology.autobuy.utils.base.BaseFragment
import com.technology.autobuy.utils.interfaces.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class ManufacturersFragment :
    BaseFragment<FragmentManufacturersBinding, IManufacturers.ViewModel>(), IManufacturers.View {
    override fun getLayoutId() = R.layout.fragment_manufacturers
    override fun getViewBinding() = FragmentManufacturersBinding.inflate(layoutInflater)
    override val viewModel: ManufacturersVM by viewModels()

    @Inject
    lateinit var pagingAdapter: ManufacturerPagingAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            with(pagingAdapter) {
                rvManufacturer.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                }
                rvManufacturer.adapter = withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                )
                swipeRefresh.setOnRefreshListener { refresh() }
                manufacturerClickListener = itemClickListener

                with(viewModel) {
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                        manufacturerFlow.collectLatest { submitData(it) }
                    }
                    viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                        loadStateFlow.collectLatest {
                            swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                        }
                    }
                }
            }
        }
    }

    override val itemClickListener = object : ItemClickListener {
        override fun onClick(data: Any?, position: Int) {
            findNavController().navigate(
                R.id.action_manufacturersFragment_to_carTypeFragment,
                bundleOf(MANUFACTURER_ITEM_KEY to data as ManufacturerItem)
            )
        }
    }

}