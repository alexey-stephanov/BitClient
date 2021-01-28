package com.example.bitclient.data.network.datamodels.branchesmodel

import com.example.bitclient.data.network.datamodels.PaginatedResponse

class BranchesResponse(data: List<BranchModel>) : PaginatedResponse<BranchModel>(data)