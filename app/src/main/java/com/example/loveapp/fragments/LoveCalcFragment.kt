package com.example.loveapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.loveapp.App
import com.example.loveapp.databinding.FragmentLoveCalcBinding
import com.example.loveapp.model.LoveResult
import com.example.loveapp.viewmodel.LoveCalcViewModel
import com.example.loveapp.viewmodel.LoveCalcViewModelFactory

class LoveCalcFragment : Fragment() {

    private val binding by lazy {
        FragmentLoveCalcBinding.inflate(layoutInflater)
    }

    private val viewModel: LoveCalcViewModel by viewModels {
        LoveCalcViewModelFactory((requireActivity().application as App).api)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalc.setOnClickListener {
            viewModel.calculateLove(
                firstName = binding.etFirstName.text.toString(),
                secondName = binding.etSecondName.text.toString()
            )
        }

        viewModel.loveResult.observe(viewLifecycleOwner) { loveResult ->
            saveResult(loveResult)
            val action = LoveCalcFragmentDirections.actionLoveCalcFragmentToLoveResultFragment(
                firstName = loveResult.firstName,
                secondName = loveResult.secondName,
                percentage = loveResult.percentage,
                result = loveResult.result
            )

            findNavController().navigate(action)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        binding.btnHistory.setOnClickListener {
            findNavController().navigate(LoveCalcFragmentDirections.actionLoveCalcFragmentToHistoryFragment())
        }
    }

    private fun saveResult(loveResult: LoveResult) {
        App().getInstance()?.loveDao()?.insert(loveResult)
    }
}
