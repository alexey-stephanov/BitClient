package com.example.bitclient.data.storage

interface Storage {
    fun getString(key: String): String
    fun setString(key: String, value: String)
}