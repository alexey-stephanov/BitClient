package com.example.bitclient.ui.recyclerview

fun interface OnItemClickListener<T> {
    fun onItemClick(data: T)
}