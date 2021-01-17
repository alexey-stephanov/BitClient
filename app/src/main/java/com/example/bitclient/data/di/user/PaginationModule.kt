package com.example.bitclient.data.di.user

import com.example.bitclient.data.network.networkmodels.PaginatedResponse
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.requests.UserDataRepository
import com.example.bitclient.data.pagination.PagingDataSource
import dagger.Module
import dagger.Provides

@Module
class PaginationModule {
//
//    @Provides
//    fun provideRetrievingRepositories(userDataRepository: UserDataRepository): suspend (Int) -> PaginatedResponse<RepositoriesResponse> {
//        return {userDataRepository.retrieveUserRepositories()}
//    }
//
//    @JvmSuppressWildcards
//    @Provides
//    fun providePagingDataSource(retrieveData: suspend (Int) -> PaginatedResponse<RepositoriesResponse>): PagingDataSource<RepositoriesResponse> {
//        return PagingDataSource { page ->  retrieveData(page) }
//    }
}