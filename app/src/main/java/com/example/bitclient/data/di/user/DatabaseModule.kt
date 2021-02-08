package com.example.bitclient.data.di.user

import android.content.Context
import androidx.room.Room
import com.example.bitclient.data.database.AccountDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @UserScope
    @Provides
    fun provideAccountDatabase(context: Context): AccountDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AccountDatabase::class.java,
            "account-db"
        ).build()
    }
}