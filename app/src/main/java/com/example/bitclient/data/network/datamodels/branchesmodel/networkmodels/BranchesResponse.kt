package com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels

import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedResponse

class BranchesResponse(data: List<BranchModel>) : PaginatedResponse<BranchModel>(data)