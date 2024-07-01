package com.example.loveapp.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.loveapp.R
import com.example.loveapp.databinding.FragmentOnboardBinding
import com.example.loveapp.model.utils.PreferenceHelper

class OnboardFragment : Fragment() {

    private val binding by lazy {
        FragmentOnboardBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferenceHelper = PreferenceHelper()
        preferenceHelper.unit(requireContext())

        binding.tvStart.setOnClickListener {
            preferenceHelper.isOnboardShown = true
            findNavController().navigate(R.id.action_onboardFragment_to_loveCalcFragment)
        }
    }
}