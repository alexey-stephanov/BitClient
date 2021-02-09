package com.example.bitclient.data.network.datamodels.repositoriesmodel

import com.example.bitclient.data.network.datamodels.NetworkToDbDataMapper
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.toRepositoryDbModel
import javax.inject.Inject

class RepositoryDataMapper @Inject constructor(): NetworkToDbDataMapper<RepositoryModel, RepositoryDbModel> {
    override fun convert(dataModel: RepositoryModel, page: Int): RepositoryDbModel =
        dataModel.toRepositoryDbModel(page)
}