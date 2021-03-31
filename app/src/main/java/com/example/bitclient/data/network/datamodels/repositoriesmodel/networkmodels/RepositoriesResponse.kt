package com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels

import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedResponse

class RepositoriesResponse(data: List<RepositoryModel>) : PaginatedResponse<RepositoryModel>(data)