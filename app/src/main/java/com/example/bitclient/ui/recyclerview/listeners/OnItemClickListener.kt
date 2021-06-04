package com.example.bitclient.ui.recyclerview.listeners

fun interface OnItemClickListener<DbDataModel> {
    fun onItemClick(data: DbDataModel)
}