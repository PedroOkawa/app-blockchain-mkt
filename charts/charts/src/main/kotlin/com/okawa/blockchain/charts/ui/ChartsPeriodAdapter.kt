package com.okawa.blockchain.charts.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.okawa.blockchain.charts.model.ChartsPeriod
import com.okawa.blockchain.mkt.R
import com.okawa.blockchain.mkt.databinding.AdapterPeriodBinding

class ChartsPeriodAdapter(
    context: Context
) : ArrayAdapter<ChartsPeriod>(
    context,
    R.layout.adapter_period,
    ChartsPeriod.values()
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.adapter_period, parent, false)
        val binding = AdapterPeriodBinding.bind(view)
        binding.tvTitle.text = context.getString(ChartsPeriod.values()[position].value)
        return binding.root
    }
}