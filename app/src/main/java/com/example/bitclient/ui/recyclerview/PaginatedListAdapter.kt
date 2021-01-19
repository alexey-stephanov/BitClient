package com.example.bitclient.ui.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bitclient.databinding.RepositoryItemBinding

abstract class PaginatedListAdapter<DataModel : Any> :
    PagingDataAdapter<DataModel, RecyclerView.ViewHolder>(PagingComparator<DataModel>()) {

    private lateinit var binding: ViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RepositoryItemBinding.inflate(inflater)
        return getViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<DataModel>).bind(getItem(position)!!)
    }

    override fun getItemViewType(position: Int): Int =
        getLayoutId(position, getItem(position)!!)

    protected abstract fun getLayoutId(position: Int, obj: DataModel): Int

    protected open fun getViewHolder(binding: ViewBinding, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.create(binding, viewType)
    }

    interface Binder<DataModel> {
        fun bind(data: DataModel)
    }

    class PagingComparator<DataModel : Any> : DiffUtil.ItemCallback<DataModel>() {
        override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean =
            oldItem.toString() == newItem.toString()

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean =
            oldItem == newItem
    }
}