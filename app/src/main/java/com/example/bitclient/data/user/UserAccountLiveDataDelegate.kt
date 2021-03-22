package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel

interface UserAccountLiveDataDelegate {
    val liveAccountModel: MutableLiveData<AccountDbModel>
}