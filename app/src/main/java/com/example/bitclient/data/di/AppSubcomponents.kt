package com.example.bitclient.data.di

import com.example.bitclient.data.di.authorization.AuthorizationSubcomponent
import com.example.bitclient.data.di.user.UserSubcomponent
import dagger.Module

@Module(subcomponents = [AuthorizationSubcomponent::class, UserSubcomponent::class])
class AppSubcomponents