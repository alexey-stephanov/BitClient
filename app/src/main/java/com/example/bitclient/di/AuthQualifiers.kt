package com.example.bitclient.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Authorization

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Requests