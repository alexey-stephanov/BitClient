package com.example.bitclient.data.di.user

import android.content.Context
import androidx.room.Room
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.RepositoriesDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @UserScope
    @AccountDbQualifier
    @Provides
    fun provideAccountDatabase(context: Context): AccountDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AccountDatabase::class.java,
            "account-db"
        ).build()
    }

    @UserScope
    @RepositoriesDbQualifier
    @Provides
    fun provideRepositoriesDatabase(context: Context): RepositoriesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RepositoriesDatabase::class.java,
            "repositories-db"
        ).build()
    }
}