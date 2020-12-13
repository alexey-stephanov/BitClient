package com.example.bitclient.di

import dagger.Module

@Module(subcomponents = [AuthorizationComponent::class, UserComponent::class])
class AppSubcomponents