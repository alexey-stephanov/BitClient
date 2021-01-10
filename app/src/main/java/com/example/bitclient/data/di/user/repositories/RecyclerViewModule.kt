package com.example.bitclient.data.di.user.repositories

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.ui.adapters.RepositoriesListAdapter
import dagger.Module
import dagger.Provides

@Module
class RecyclerViewModule {

    @RepositoriesScope
    @Provides
    fun provideRepositoriesListAdapter(): RepositoriesListAdapter = RepositoriesListAdapter()

    @RepositoriesScope
    @Provides
    fun provideDividerItemDecoration(context: Context): DividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
}