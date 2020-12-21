package com.example.bitclient.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class BasicAuth

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class BearerAuth