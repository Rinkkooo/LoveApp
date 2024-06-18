package com.example.loveapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.loveapp.App
import com.example.loveapp.LoveResult
import com.example.loveapp.databinding.FragmentLoveCalcBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveCalcFragment : Fragment() {

    private val binding by lazy {
        FragmentLoveCalcBinding.inflate(layoutInflater)
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
            App().api.getPercentage(
                key = "13db8c0c9fmsh0e8b65404615b3ap1035a5jsn85bfe5faab5c",
                host = "love-calculator.p.rapidapi.com",
                firstName = binding.etFirstName.text.toString(),
                secondName = binding.etSecondName.text.toString()
            ).enqueue(object : Callback<LoveResult> {
                override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                    if (response.isSuccessful && response.body() != null) {
                        val loveResult = response.body()!!

                        val action = LoveCalcFragmentDirections.actionLoveCalcFragmentToLoveResultFragment(
                            firstName = binding.etFirstName.text.toString(),
                            secondName = binding.etSecondName.text.toString(),
                            percentage = loveResult.percentage,
                            result = loveResult.result
                        )
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Error: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoveResult>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }
}