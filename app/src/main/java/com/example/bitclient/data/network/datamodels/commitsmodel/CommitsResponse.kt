package com.example.bitclient.data.network.datamodels.commitsmodel

import com.example.bitclient.data.network.datamodels.PaginatedResponse

class CommitsResponse(data: List<CommitModel>) : PaginatedResponse<CommitModel>(data)