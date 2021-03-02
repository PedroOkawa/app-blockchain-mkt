package com.okawa.blockchain.mkt.feature.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.okawa.blockchain.mkt.databinding.FragmentTutorialPageBinding

class TutorialPageFragment : Fragment() {

    companion object {
        private const val TUTORIAL_PAGE_IMAGE = "args:tutorial_image"
        private const val TUTORIAL_PAGE_DESCRIPTION = "args:tutorial_description"

        fun newInstance(
            @DrawableRes imageRes: Int,
            @StringRes descriptionRes: Int
        ): TutorialPageFragment {
            return TutorialPageFragment().apply {
                arguments = bundleOf(
                    TUTORIAL_PAGE_IMAGE to imageRes,
                    TUTORIAL_PAGE_DESCRIPTION to descriptionRes
                )
            }
        }
    }

    private lateinit var binding: FragmentTutorialPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTutorialPageBinding.inflate(inflater, container, false)
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        val imageRes = requireArguments().getInt(TUTORIAL_PAGE_IMAGE)
        val descriptionRes = requireArguments().getInt(TUTORIAL_PAGE_DESCRIPTION)

        binding.tvDescription.setText(descriptionRes)
    }
}