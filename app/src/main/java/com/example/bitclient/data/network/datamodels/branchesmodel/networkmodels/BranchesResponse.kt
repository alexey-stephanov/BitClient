package com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels

import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse

class BranchesResponse(data: List<BranchModel>) : PaginatedResponse<BranchModel>(data)