package com.example.bitclient.data.di.user

import com.example.bitclient.data.di.user.account.AccountSubcomponent
import com.example.bitclient.data.di.user.repositories.RepositoriesSubcomponent
import com.example.bitclient.data.di.user.settings.SettingsSubcomponent
import dagger.Module

@Module(subcomponents = [AccountSubcomponent::class, RepositoriesSubcomponent::class, SettingsSubcomponent::class])
class UserSubcomponents