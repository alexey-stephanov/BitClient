package com.example.bitclient.di.user.repositories

import com.example.bitclient.di.user.repositories.branches.BranchesSubcomponent
import dagger.Module

@Module(subcomponents = [BranchesSubcomponent::class])
class RepositoriesSubcomponents