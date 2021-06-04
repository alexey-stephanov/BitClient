package com.example.bitclient.ui.recyclerview.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.ui.recyclerview.AdapterListener
import com.example.bitclient.ui.recyclerview.Cell
import com.example.bitclient.ui.recyclerview.CellTypes
import com.example.bitclient.ui.recyclerview.models.RecyclerItem

//class BaseListAdapter(
//    vararg types: Cell<RecyclerItem>,
//    private val listener: AdapterListener? = null
//) : ListAdapter<RecyclerItem, RecyclerView.ViewHolder>(BASE_DIFF_CALLBACK) {
//
//    private val cellTypes: CellTypes<RecyclerItem> = CellTypes(*types)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
//        cellTypes.of(viewType).holder(parent)
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val item = getItem(position)
//        return cellTypes.of(item).bind(holder, item, listener)
//    }
//
//    override fun getItemViewType(position: Int): Int =
//        cellTypes.of(getItem(position)).type()
//
//    companion object {
//        val BASE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecyclerItem>() {
//            override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean =
//                oldItem.id == newItem.id
//
//            override fun areContentsTheSame(
//                oldItem: RecyclerItem,
//                newItem: RecyclerItem
//            ): Boolean =
//                oldItem == newItem
//        }
//    }
//}