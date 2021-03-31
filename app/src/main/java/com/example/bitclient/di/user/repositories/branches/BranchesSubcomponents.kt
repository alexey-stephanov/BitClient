package com.example.bitclient.di.user.repositories.branches

import com.example.bitclient.di.user.repositories.branches.commits.CommitsSubcomponent
import dagger.Module

@Module(subcomponents = [CommitsSubcomponent::class])
class BranchesSubcomponents