package com.example.bitclient.data.di.user.repositories

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides

@Module
class RecyclerViewModule {

    @RepositoriesScope
    @Provides
    fun provideDividerItemDecoration(context: Context): DividerItemDecoration =
        DividerItemDecoration(context, RecyclerView.VERTICAL)
}