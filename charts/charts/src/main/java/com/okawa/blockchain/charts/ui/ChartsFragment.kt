package com.okawa.blockchain.charts.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.okawa.blockchain.charts.R
import com.okawa.blockchain.charts.databinding.FragmentChartsBinding
import com.okawa.blockchain.charts.model.Charts
import com.okawa.blockchain.charts.model.ChartsPeriod
import com.okawa.blockchain.charts.utils.inject
import javax.inject.Inject

class ChartsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ChartsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ChartsViewModel::class.java)
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
        setupViews()
        setupObservers()
        return binding.root
    }

    private fun setupViews() {
        binding.lcContent.description.isEnabled = false
        binding.spPeriod.apply {
            adapter = ChartsPeriodAdapter(requireContext())
            binding.spPeriod.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    val period: ChartsPeriod = parent.getItemAtPosition(position) as ChartsPeriod
                    viewModel.retrieveCharts(period)
                }

                override fun onNothingSelected(parent: AdapterView<*>) { }
            }
            setSelection(ChartsPeriod.ONE_WEEK.ordinal)
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { onViewState(it) })
    }

    private fun onViewState(viewState: ChartsViewModel.ViewState) {
        when (viewState) {
            is ChartsViewModel.ViewState.Error -> onViewStateError()
            is ChartsViewModel.ViewState.Loading -> onViewStateLoading()
            is ChartsViewModel.ViewState.Success -> onViewStateSuccess(viewState.charts)
        }
    }

    private fun onViewStateError() {
        Log.w("TEST", "ERROR")
    }

    private fun onViewStateLoading() {
        Log.w("TEST", "LOADING")
    }

    private fun onViewStateSuccess(charts: Charts) {
        Log.w("TEST", "SUCCESS: $charts")
        binding.tvDescription.text = charts.description

        val entries = charts.values.mapIndexed { index, value ->
            Entry(index.toFloat(), value.value)
        }

        val lineDataSet = LineDataSet(entries, charts.name).also { set ->
            set.color = ContextCompat.getColor(requireContext(), R.color.chartLineColor)
            set.highLightColor = ContextCompat.getColor(requireContext(), R.color.chartHighlightColor)
            set.axisDependency = YAxis.AxisDependency.LEFT
            set.lineWidth = 2f
            set.setDrawCircles(false)
            set.setDrawValues(false)
            set.setDrawCircleHole(false)
        }
        val lineData = LineData(lineDataSet)

        binding.lcContent.apply {
            data = lineData
            invalidate()
        }
    }
}