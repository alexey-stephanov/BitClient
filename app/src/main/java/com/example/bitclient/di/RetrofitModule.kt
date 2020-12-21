package com.example.bitclient.di

import com.example.bitclient.BuildConfig
import com.example.bitclient.data.oauth.BasicAuthInterceptor
import com.example.bitclient.data.oauth.BearerAuthInterceptor
import com.example.bitclient.data.storage.Storage
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @BasicAuth
    @Provides
    fun provideBasicRetrofit(@BasicAuth okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
            .baseUrl("https://bitbucket.org/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Singleton
    @BearerAuth
    @Provides
    fun provideBearerRetrofit(@BearerAuth okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
            .baseUrl("https://api.bitbucket.org/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Singleton
    @BasicAuth
    @Provides
    fun provideBasicAuthClient(
            @BasicAuth interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @BearerAuth
    @Provides
    fun provideBearerAuthClient(
            @BearerAuth interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @BasicAuth
    @Provides
    fun provideBasicAuthInterceptor(): Interceptor = BasicAuthInterceptor(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)

    @Singleton
    @BearerAuth
    @Provides
    fun provideBearerAuthInterceptor(storage: Storage): Interceptor = BearerAuthInterceptor(storage)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
}