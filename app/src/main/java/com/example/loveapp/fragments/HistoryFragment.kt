package com.example.loveapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.loveapp.App
import com.example.loveapp.R
import com.example.loveapp.databinding.FragmentHistoryBinding
import com.example.loveapp.fragments.adapters.HistoryAdapter
import com.example.loveapp.model.LoveResult
import com.example.loveapp.model.utils.OnClickItem

class HistoryFragment : Fragment(), OnClickItem {

    private val binding by lazy { FragmentHistoryBinding.inflate(layoutInflater) }
    private val historyAdapter = HistoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHistory.adapter = historyAdapter
        App().getInstance()?.loveDao()?.getAll()
            ?.observe(viewLifecycleOwner, Observer { loveResult ->
                historyAdapter.submitList(loveResult)
            })

    }

    override fun onLongClick(loveResult: LoveResult) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Do you want to delete it?")
            setPositiveButton("Yes") { dialog, which ->
                App().getInstance()?.loveDao()?.delete(loveResult)
            }
            setNegativeButton("Нет") { dialog, which ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }
}
