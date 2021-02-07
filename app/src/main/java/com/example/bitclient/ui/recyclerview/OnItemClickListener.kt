package com.example.bitclient.ui.recyclerview

fun interface OnItemClickListener<DbDataModel> {
    fun onItemClick(data: DbDataModel)
}