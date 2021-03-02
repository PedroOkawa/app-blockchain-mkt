package com.okawa.blockchain.pools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.okawa.blockchain.pools.databinding.FragmentPoolsBinding

class PoolsFragment : Fragment() {

    private lateinit var binding: FragmentPoolsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPoolsBinding.inflate(inflater, container, false)
        return binding.root
    }
}