package com.example.bitclient.ui.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel

abstract class PaginatedListAdapter<DataDbModel : PaginatedDbModel>(
    private var listener: OnItemClickListener<DataDbModel>? = null,
    private val bindingInflater: (inflater: LayoutInflater, parent: ViewGroup) -> ViewBinding
) : PagingDataAdapter<DataDbModel, RecyclerView.ViewHolder>(PagingComparator<DataDbModel>()) {

    private lateinit var binding: ViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = bindingInflater(inflater, parent)
        return getViewHolder(binding, viewType)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<DataDbModel>).bind(getItem(position)!!, listener)
    }

    override fun getItemViewType(position: Int): Int =
        getLayoutId(position, getItem(position)!!)

    protected abstract fun getLayoutId(position: Int, obj: DataDbModel): Int

    private fun getViewHolder(binding: ViewBinding, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.create(binding, viewType)
    }

    interface Binder<DataDbModel : PaginatedDbModel> {
        fun bind(data: DataDbModel, listener: OnItemClickListener<DataDbModel>?)
    }

    class PagingComparator<DataDbModel : PaginatedDbModel> : DiffUtil.ItemCallback<DataDbModel>() {
        override fun areItemsTheSame(oldItem: DataDbModel, newItem: DataDbModel): Boolean =
            oldItem.unique == newItem.unique

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataDbModel, newItem: DataDbModel): Boolean =
            oldItem == newItem
    }
}