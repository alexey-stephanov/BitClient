package com.example.bitclient.data.network.datamappers

import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.toCommitDbModel
import javax.inject.Inject

class CommitDataMapper @Inject constructor(): NetworkToDbDataMapper<CommitModel, CommitDbModel> {
    override fun convert(dataModel: CommitModel, page: Int): CommitDbModel =
        dataModel.toCommitDbModel(page)
}