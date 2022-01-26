package com.technology.autobuy.ui.cartypedates

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.technology.autobuy.R
import com.technology.autobuy.data.models.ManufacturerItem
import com.technology.autobuy.databinding.FragmentCarDateBinding
import com.technology.autobuy.ui.adapters.ListAdapter
import com.technology.autobuy.utils.base.BaseFragment
import com.technology.autobuy.utils.base.UIState
import com.technology.autobuy.utils.toGone
import com.technology.autobuy.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarTypeDateFragment : BaseFragment<FragmentCarDateBinding, ICarTypeDate.ViewModel>(),
    ICarTypeDate.View {
    override fun getLayoutId() = R.layout.fragment_car_date
    override fun getViewBinding(): FragmentCarDateBinding =
        FragmentCarDateBinding.inflate(layoutInflater)

    override val viewModel: CarTypeDateVM by viewModels()

    @Inject
    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { viewModel.setBundleValues(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {

            with(binding) {
                tvTitle.text =
                    getString(R.string.screen_car_model_year_title_text, manufacturerItem?.name)
                binding.rvModelsByDate.adapter = adapter
                rvModelsByDate.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                }
            }
        }
        viewModelObservers()

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }
        binding.btnRetry.setOnClickListener {
            viewModel.refreshData()
        }
    }

    override fun viewModelObservers() {
        viewModel.modelsByYear.observe(viewLifecycleOwner, {
            setCarModeList(it)
        })

        viewModel.uiState.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = false
            handleUIState(it)
        })
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
            binding.rvModelsByDate.adapter = adapter
        } else {
            showErrorView()
        }
    }

    override fun showDataView() {
        binding.swipeRefresh.isRefreshing = false
        binding.rvModelsByDate.toVisible()
        binding.errorView.toGone()
    }

    override fun showLoadingView() {
        binding.swipeRefresh.isRefreshing = true
        binding.errorView.toGone()
        binding.rvModelsByDate.toGone()
    }

    override fun showErrorView() {
        binding.swipeRefresh.isRefreshing = false
        binding.errorView.toVisible()
        binding.rvModelsByDate.toGone()
    }
}