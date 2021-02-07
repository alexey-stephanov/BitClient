package com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels

import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse

class CommitsResponse(data: List<CommitModel>) : PaginatedResponse<CommitModel>(data)