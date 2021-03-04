package com.okawa.blockchain.pools.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.okawa.blockchain.pools.R
import com.okawa.blockchain.pools.databinding.FragmentPoolsBinding
import com.okawa.blockchain.pools.model.Pools
import com.okawa.blockchain.pools.model.PoolsPeriod
import com.okawa.blockchain.pools.ui.PoolsViewModel.ViewState
import com.okawa.blockchain.pools.utils.*
import javax.inject.Inject

class PoolsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PoolsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(PoolsViewModel::class.java)
    }
    private lateinit var binding: FragmentPoolsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPoolsBinding.inflate(inflater, container, false)
        setupViews()
        setupObservers()
        return binding.root
    }

    private fun setupViews() {
        setupChart()
        setupSpinner()
    }

    private fun setupChart() {
        binding.lcContent.apply {
            legend.isEnabled = false
            description.isEnabled = false
            transparentCircleRadius = getGraphTransparentCircleRadius()
            holeRadius = getGraphHoleRadius()
            setEntryLabelColor(getGraphLabelColor())
            setHoleColor(Color.TRANSPARENT)
            setDrawCenterText(true)
            setUsePercentValues(true)
        }
    }

    private fun setupSpinner() {
        binding.spPeriod.apply {
            adapter = PoolsPeriodAdapter(requireContext())
            binding.spPeriod.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val period: PoolsPeriod = parent.getItemAtPosition(position) as PoolsPeriod
                    viewModel.retrievePools(period)
                }

                override fun onNothingSelected(parent: AdapterView<*>) { }
            }
            setSelection(PoolsPeriod.TWENTY_FOUR_HOURS.ordinal)
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { onViewState(it) })
    }

    private fun onViewState(viewState: ViewState) {
        when (viewState) {
            is ViewState.Error -> onViewStateError()
            is ViewState.Loading -> onViewStateLoading()
            is ViewState.Success -> onViewStateSuccess(viewState.pools)
        }
    }

    private fun onViewStateError() {
        binding.gpContent.visibility = View.GONE
        binding.laLoading.visibility = View.GONE
    }

    private fun onViewStateLoading() {
        binding.gpContent.visibility = View.GONE
        binding.laLoading.visibility = View.VISIBLE
    }

    private fun onViewStateSuccess(pools: Pools) {
        val entries = pools.miningPools.map {
            PieEntry(it.distribution.toFloat(), it.name)
        }

        val pieDataSet = PieDataSet(entries, "").also { set ->
            set.colors = getGraphDataColors()
            set.setDrawIcons(false)
            set.sliceSpace = getGraphSliceSpace()
        }
        val pieData = PieData(pieDataSet).also { data ->
            data.setValueTextColor(ContextCompat.getColor(requireContext(), R.color.chart_pie_label_color))
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(16f)
        }

        binding.apply {
            laLoading.visibility = View.GONE
            gpContent.visibility = View.VISIBLE
            lcContent.data = pieData
            lcContent.invalidate()
        }
    }
}