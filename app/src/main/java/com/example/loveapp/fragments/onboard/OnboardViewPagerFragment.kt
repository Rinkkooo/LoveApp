package com.example.loveapp.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loveapp.R
import com.example.loveapp.databinding.FragmentOnboardViewPagerBinding

class OnboardViewPagerFragment : Fragment() {

    private val binding by lazy {
        FragmentOnboardViewPagerBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}