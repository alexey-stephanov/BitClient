package com.example.bitclient.di

import android.content.Context
import androidx.room.Room
import com.example.bitclient.data.database.AccountDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAccountDatabase(context: Context): AccountDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AccountDatabase::class.java,
            "account-db"
        ).build()
    }
}