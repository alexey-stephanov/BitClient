package com.example.bitclient.di.user

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.di.user.repositories.RepositoriesScope
import dagger.Module
import dagger.Provides

@Module
class RecyclerViewModule {

    @UserScope
    @Provides
    fun provideDividerItemDecoration(context: Context): DividerItemDecoration =
        DividerItemDecoration(context, RecyclerView.VERTICAL)
}