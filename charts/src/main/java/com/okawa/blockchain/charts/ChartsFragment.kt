package com.okawa.blockchain.charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.okawa.blockchain.charts.databinding.FragmentChartsBinding

class ChartsFragment : Fragment() {

    private lateinit var binding: FragmentChartsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChartsBinding.inflate(inflater, container, false)
        return binding.root
    }
}