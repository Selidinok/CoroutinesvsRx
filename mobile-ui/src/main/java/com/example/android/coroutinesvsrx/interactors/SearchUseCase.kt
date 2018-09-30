package com.example.android.coroutinesvsrx.interactors

import com.domain.core.base.SingleUseCase
import com.domain.core.result.Result
import com.example.android.coroutinesvsrx.repository.SearchRepository
import kotlinx.coroutines.experimental.async
import com.domain.data.entitys.Deal
import com.domain.data.entitys.Merchant

/**
 * Created by User on 18:39 30.06.2018.

 */
class SearchUseCase : SingleUseCase<Pair<>> {

    val handler = object : kotlinx.coroutines.experimental.CompletionHandler {
        override fun invoke(cause: Throwable?) {

        }
    }

    suspend fun run(params: Params) {
        val merchantsResults = SearchRepository().getMerchants(params.text)
        if (merchantsResults.isSuccess) {
            //example: save merchants in DB
//            db.saveAll(merchantsResults)
        }

        val dealResults = SearchRepository().getDeals(params.text)
        if (dealResults.isSuccess) {
            //example: save deals in DB
//            db.saveAll(dealResults)
            val deal = dealResults
            async(handler = handler) {  }
        }

        val pair = merchantsResults to dealResults

        return Result.Success(pair)
    }


    data class Params(val text: String)
}