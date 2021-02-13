package com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels

import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse

class WorkspacesResponse(data: List<WorkspaceModel>) : PaginatedResponse<WorkspaceModel>(data)