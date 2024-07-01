package com.example.loveapp.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.loveapp.databinding.ItemHistoryBinding
import com.example.loveapp.model.LoveResult
import com.example.loveapp.model.utils.OnClickItem


class HistoryAdapter(private val onLongClickItem: OnClickItem) :
    ListAdapter<LoveResult, HistoryAdapter.ViewHolder>(DiffCallBack()) {
    class ViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LoveResult) {
            binding.tvFirstName.text = item.firstName
            binding.tvSecondName.text = item.secondName
            binding.tvPercentage.text = item.percentage + "%"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnLongClickListener {
            onLongClickItem.onLongClick(getItem(position))
            true
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<LoveResult>() {
        override fun areItemsTheSame(oldItem: LoveResult, newItem: LoveResult): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LoveResult, newItem: LoveResult): Boolean {
            return oldItem.id == newItem.id
        }
    }
}