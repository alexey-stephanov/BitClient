package com.example.bitclient.ui.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.databinding.RepositoryItemBinding

abstract class PaginatedListAdapter<T : Any> :
    PagingDataAdapter<T, RecyclerView.ViewHolder>(PagingComparator<T>()) {

    private lateinit var binding: ViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RepositoryItemBinding.inflate(inflater)
        return getViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(getItem(position)!!)
    }

    override fun getItemViewType(position: Int): Int =
        getLayoutId(position, getItem(position)!!)

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    protected open fun getViewHolder(binding: ViewBinding, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.create(binding, viewType)
    }

    interface Binder<T> {
        fun bind(data: T)
    }

    class PagingComparator<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem.toString() == newItem.toString()

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem
    }
}