package com.example.bitclient.data.network.datamodels.branchesmodel

import com.example.bitclient.data.network.datamodels.NetworkToDbDataMapper
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.toBranchDbModel
import javax.inject.Inject

class BranchDataMapper @Inject constructor(): NetworkToDbDataMapper<BranchModel, BranchDbModel> {
    override fun convert(dataModel: BranchModel, page: Int): BranchDbModel =
        dataModel.toBranchDbModel(page)
}