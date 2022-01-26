package com.technology.autobuy.ui.cartypes

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.technology.autobuy.R
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.databinding.FragmentCarTypeBinding
import com.technology.autobuy.ui.adapters.ListAdapter
import com.technology.autobuy.utils.CAR_MODEL_ITEM
import com.technology.autobuy.utils.MANUFACTURER_ITEM_KEY
import com.technology.autobuy.utils.base.BaseFragment
import com.technology.autobuy.utils.base.UIState
import com.technology.autobuy.utils.interfaces.ItemClickListener
import com.technology.autobuy.utils.toGone
import com.technology.autobuy.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarTypeFragment : BaseFragment<FragmentCarTypeBinding, ICarType.ViewModel>(), ICarType.View {
    override fun getLayoutId() = R.layout.fragment_car_type
    override fun getViewBinding() = FragmentCarTypeBinding.inflate(layoutInflater)
    override val viewModel: CarTypeVM by viewModels()

    @Inject
    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { viewModel.setBundleValues(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        with(binding) {
            tvTitle.text =
                getString(R.string.screen_car_model_title_text, viewModel.manufacturerItem?.name)
            postponeEnterTransition()
            rvCarType.viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
            rvCarType.adapter = adapter
            adapter.itemClickListener = listItemClickListener

        }
    }

    override fun setObservers() {
        viewModel.carTypes.observe(viewLifecycleOwner, {
            setCarModeList(it)
        })
        viewModel.uiState.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = false
            handleUIState(it)
        })
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
        binding.btnRetry.setOnClickListener {
            viewModel.refreshData()
        }
        binding.etSearch.doAfterTextChanged {
            viewModel.getFilteredData(it.toString())
        }
    }

    override val listItemClickListener = object : ItemClickListener {
        override fun onClick(data: Any?, position: Int) {
            findNavController().navigate(
                R.id.action_carTypeFragment_to_carTypeDateFragment,
                bundleOf(
                    MANUFACTURER_ITEM_KEY to viewModel.manufacturerItem,
                    CAR_MODEL_ITEM to data as ManufacturerItem
                )
            )
        }
    }

    private fun handleUIState(it: UIState) {
        when (it) {
            is UIState.Loading -> {
                showLoadingView()
            }
            is UIState.Error -> {
                showErrorView()
            }
        }
    }

    override fun setCarModeList(list: MutableList<ManufacturerItem>) {
        if (!(list.isNullOrEmpty())) {
            showDataView()
            adapter.setList(list)
            binding.rvCarType.adapter = adapter
        } else {
            showErrorView()
        }
    }

    override fun showDataView() {
        binding.swipeRefresh.isRefreshing = false

        binding.rvCarType.toVisible()
        binding.errorView.toGone()
    }

    override fun showLoadingView() {
        binding.swipeRefresh.isRefreshing = true
        binding.errorView.toGone()
        binding.rvCarType.toGone()
    }

    override fun showErrorView() {
        binding.swipeRefresh.isRefreshing = false
        binding.errorView.toVisible()
        binding.rvCarType.toGone()
    }
}