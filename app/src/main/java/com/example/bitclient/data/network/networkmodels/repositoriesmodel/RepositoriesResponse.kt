package com.example.bitclient.data.network.networkmodels.repositoriesmodel

import com.example.bitclient.data.network.networkmodels.PaginatedResponse

class RepositoriesResponse(data: List<RepositoryModel>) : PaginatedResponse<RepositoryModel>(data)