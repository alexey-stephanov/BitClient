package com.example.bitclient.data.network.datamodels.repositoriesmodel

import com.example.bitclient.data.network.datamodels.PaginatedResponse

class RepositoriesResponse(data: List<RepositoryModel>) : PaginatedResponse<RepositoryModel>(data)