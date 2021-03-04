package com.okawa.blockchain.pools.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.okawa.blockchain.mkt.R
import com.okawa.blockchain.mkt.databinding.AdapterPeriodBinding
import com.okawa.blockchain.pools.model.PoolsPeriod

class PoolsPeriodAdapter(
    context: Context
) : ArrayAdapter<PoolsPeriod>(
    context,
    R.layout.adapter_period,
    PoolsPeriod.values()
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
        binding.tvTitle.text = context.getString(PoolsPeriod.values()[position].value)
        return binding.root
    }
}