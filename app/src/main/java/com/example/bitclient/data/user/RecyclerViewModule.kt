package com.example.bitclient.data.user

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.data.pagination.RepositoryDataSource
import com.example.bitclient.data.network.requests.RequestsDataRepository
import com.example.bitclient.ui.adapters.RepositoriesListAdapter
import dagger.Module
import dagger.Provides

@Module
class RecyclerViewModule {

//  @Provides
//  fun provideRepositoryDataSource(requestsDataRepository: RequestsDataRepository): RepositoryDataSource = RepositoryDataSource(requestsDataRepository)

    @Provides
    fun provideRepositoriesListAdapter(): RepositoriesListAdapter = RepositoriesListAdapter()

    @Provides
    fun provideDividerItemDecoration(context: Context): DividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
}