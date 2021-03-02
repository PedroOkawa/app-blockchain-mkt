package com.okawa.blockchain.mkt.feature.tutorial

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.okawa.blockchain.mkt.databinding.FragmentTutorialBinding
import com.okawa.blockchain.mkt.feature.home.HomeActivity
import com.okawa.blockchain.mkt.utils.debounceClick
import com.okawa.blockchain.mkt.utils.setStatusBarColor

class TutorialFragment : Fragment() {

    private val viewModel: TutorialViewModel by viewModels()

    private lateinit var binding: FragmentTutorialBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorialBinding.inflate(inflater, container, false)
        setupViews()
        setupListeners()
        setupObservers()
        return binding.root
    }

    private fun setupViews() {
        binding.content.adapter = TutorialPagerAdapter(this)
        binding.content.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.onPageSelected(position)
            }
        })
        TabLayoutMediator(binding.indicator, binding.content) { _, _ -> }.attach()
    }

    private fun setupListeners() {
        binding.btClose.debounceClick {
            viewModel.onCloseTapped()
        }
    }

    private fun setupObservers() {
        viewModel.navigation.observe(viewLifecycleOwner, { onNavigation() })
        viewModel.viewState.observe(viewLifecycleOwner, { onViewState(it) })
    }

    private fun onViewState(viewState: TutorialViewModel.ViewState) {
        val color = ContextCompat.getColor(requireContext(), viewState.colorRes)

        setStatusBarColor(color)
        binding.root.setBackgroundColor(color)
        binding.indicator.setBackgroundColor(color)
        binding.btClose.visibility = if (viewState.isLastPage) View.VISIBLE else View.GONE
    }

    private fun onNavigation() {
        startActivity(Intent(requireContext(), HomeActivity::class.java).also {
            it.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        })
        requireActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out)
    }
}