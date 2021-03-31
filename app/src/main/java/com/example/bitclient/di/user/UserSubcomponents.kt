package com.example.bitclient.di.user

import com.example.bitclient.di.user.account.AccountSubcomponent
import com.example.bitclient.di.user.repositories.RepositoriesSubcomponent
import com.example.bitclient.di.user.settings.SettingsSubcomponent
import dagger.Module

@Module(subcomponents = [AccountSubcomponent::class, RepositoriesSubcomponent::class, SettingsSubcomponent::class])
class UserSubcomponents