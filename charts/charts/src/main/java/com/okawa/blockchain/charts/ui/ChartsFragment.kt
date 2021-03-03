package com.okawa.blockchain.charts.ui

import android.content.Context
import android.os.Bundle
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
import com.okawa.blockchain.charts.databinding.FragmentChartsBinding
import com.okawa.blockchain.charts.model.Charts
import com.okawa.blockchain.charts.model.ChartsPeriod
import com.okawa.blockchain.mkt.R
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
                override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                    val period: ChartsPeriod = parent.getItemAtPosition(pos) as ChartsPeriod
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

    private fun onViewState(charts: Charts) {
        binding.tvDescription.text = charts.description

        val entries = charts.values.mapIndexed { index, value ->
            Entry(index.toFloat(), value.value)
        }

        val lineDataSet = LineDataSet(entries, charts.name).also { set ->
            set.axisDependency = YAxis.AxisDependency.LEFT
            set.color = ContextCompat.getColor(requireContext(), R.color.secondaryTextColor)
            set.setDrawCircles(false)
            set.setDrawValues(false)
            set.lineWidth = 2f
            set.highLightColor = ContextCompat.getColor(requireContext(), R.color.primaryDarkColor)
            set.setDrawCircleHole(false)
        }
        val lineData = LineData(lineDataSet)

        binding.lcContent.apply {
            data = lineData
            invalidate()
        }
    }
}