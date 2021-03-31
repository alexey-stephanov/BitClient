package com.example.bitclient.di

import com.example.bitclient.di.authorization.AuthorizationSubcomponent
import com.example.bitclient.di.user.UserSubcomponent
import dagger.Module

@Module(subcomponents = [AuthorizationSubcomponent::class, UserSubcomponent::class])
class AppSubcomponents