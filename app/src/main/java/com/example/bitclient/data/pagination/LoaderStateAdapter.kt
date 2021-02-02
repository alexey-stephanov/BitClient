package com.example.bitclient.data.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.databinding.LoadStateItemBinding

class LoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val binding = LoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderViewHolder(binding, retry)
    }

    class LoaderViewHolder(
        private val binding: LoadStateItemBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBarLoadStateItemLoading.visibility =
                    toVisibility(loadState is LoadState.Loading)
                buttonLoadStateItemRetry.visibility = toVisibility(loadState !is LoadState.Loading)
                buttonLoadStateItemRetry.setOnClickListener { retry() }
                textViewLoadStateItemErrorMessage.visibility =
                    toVisibility(loadState !is LoadState.Loading)
            }
        }

        private fun toVisibility(loadState: Boolean): Int =
            if (loadState)
                View.VISIBLE
            else
                View.GONE
    }
}