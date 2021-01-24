package com.example.bitclient.data.di.user

import android.content.Context
import androidx.room.Room
import com.example.bitclient.data.database.UserDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @UserScope
    @Provides
    fun provideUserDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user-db").build()
    }
}