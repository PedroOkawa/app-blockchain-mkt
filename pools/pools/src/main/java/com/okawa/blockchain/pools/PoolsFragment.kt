package com.okawa.blockchain.pools

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.okawa.blockchain.pools.databinding.FragmentPoolsBinding
import com.okawa.blockchain.pools.utils.inject
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
        viewModel.test()
        return binding.root
    }
}