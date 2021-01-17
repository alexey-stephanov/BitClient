package com.example.bitclient.data.di

import com.example.bitclient.data.network.events.EventProducer
import com.example.bitclient.data.network.events.EventProducerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class EventsModel {

    @Singleton
    @Binds
    abstract fun bindEventProducer(eventProducerImpl: EventProducerImpl): EventProducer
}