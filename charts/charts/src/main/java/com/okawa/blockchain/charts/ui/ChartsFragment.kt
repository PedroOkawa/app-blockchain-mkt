package com.okawa.blockchain.charts.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.okawa.blockchain.charts.databinding.FragmentChartsBinding
import com.okawa.blockchain.charts.model.Charts
import javax.inject.Inject

class ChartsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ChartsViewModel by lazy {
        ViewModelProvider(this,  viewModelFactory).get(ChartsViewModel::class.java)
    }
    private lateinit var binding: FragmentChartsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChartsBinding.inflate(inflater, container, false)
        viewModel.retrieveCharts()
        setupObservables()
        return binding.root
    }

    private fun setupObservables() {
        viewModel.viewState.observe(viewLifecycleOwner, { onViewState(it) })
    }

    private fun onViewState(charts: Charts) {
        Log.w("TEST", "STATE: $charts")
    }
}