package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.BranchDataMapper
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchModel
import com.example.bitclient.data.repositories.branches.BranchesRepository

@ExperimentalPagingApi
class BranchesViewModel(
    private val branchesRepository: BranchesRepository,
    database: AccountDatabase,
    dataMapper: BranchDataMapper,
    workspaceId: String,
    repositoryId: String
) : PaginatedViewModel<BranchModel, BranchDbModel>(
    database.branchesDao(),
    database,
    dataMapper,
    repositoryId,
    { database.branchesDao().getItemsByOwnerId(repositoryId) },
    { page -> branchesRepository.retrieveBranches(workspaceId, repositoryId, page) })