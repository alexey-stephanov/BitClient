package com.example.bitclient.data.di

import com.example.bitclient.data.network.authorization.AuthorizationComponent
import com.example.bitclient.data.user.UserComponent
import dagger.Module

@Module(subcomponents = [AuthorizationComponent::class, UserComponent::class])
class AppSubcomponents