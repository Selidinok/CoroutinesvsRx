package com.example.android.coroutinesvsrx.interactors

import com.cashback.core.base.SingleUseCase
import com.cashback.data.entitys.Merchant
import com.example.android.coroutinesvsrx.repository.SearchRepository

class GetMerchantsUseCase : SingleUseCase<List<Merchant>, GetMerchantsUseCase.Params>() {
    override suspend fun run(params: Params) = SearchRepository().getMerchants(params.text)

    data class Params(val text: String)
}