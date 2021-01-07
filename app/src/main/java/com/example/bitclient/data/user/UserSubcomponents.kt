package com.example.bitclient.data.user

import com.example.bitclient.data.user.account.AccountComponent
import com.example.bitclient.data.user.repositories.RepositoriesComponent
import com.example.bitclient.data.user.settings.SettingsComponent
import dagger.Module

@Module(subcomponents = [AccountComponent::class, RepositoriesComponent::class, SettingsComponent::class])
class UserSubcomponents