package com.example.bitclient.di

import com.example.bitclient.data.network.events.EventProducer
import com.example.bitclient.data.network.events.EventProducerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface EventsModule {

    @Singleton
    @Binds
    fun bindEventProducer(eventProducerImpl: EventProducerImpl): EventProducer
}