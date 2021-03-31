package com.example.bitclient.data.network.datamappers

import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.WorkspaceModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.toWorkspaceDbModel
import javax.inject.Inject

class WorkspaceDataMapper @Inject constructor() :
    NetworkToDbDataMapper<WorkspaceModel, WorkspaceDbModel> {
    override fun convert(dataModel: WorkspaceModel, page: Int): WorkspaceDbModel =
        dataModel.toWorkspaceDbModel(page)
}