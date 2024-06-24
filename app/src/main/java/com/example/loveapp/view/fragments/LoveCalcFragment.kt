package com.example.loveapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loveapp.App
import com.example.loveapp.databinding.FragmentLoveCalcBinding
import com.example.loveapp.presenter.LoveCalcPresenter
import com.example.loveapp.view.LoveCalcView

class LoveCalcFragment : Fragment(), LoveCalcView {

    private val binding by lazy {
        FragmentLoveCalcBinding.inflate(layoutInflater)
    }

    private val presenter by lazy {
        LoveCalcPresenter(this, App().api)
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
            presenter.calculateLove(
                firstName = binding.etFirstName.text.toString(),
                secondName = binding.etSecondName.text.toString()
            )
        }
    }

    override fun showResult(firstName: String, secondName: String, percentage: String, result: String) {
        val action = LoveCalcFragmentDirections.actionLoveCalcFragmentToLoveResultFragment(
            firstName = firstName,
            secondName = secondName,
            percentage = percentage,
            result = result
        )
        findNavController().navigate(action)
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
