package com.example.bitclient.ui.recyclerview.models

import androidx.viewbinding.ViewBinding

interface RecyclerItem {
    val id: String?
    override fun equals(other: Any?): Boolean
}
