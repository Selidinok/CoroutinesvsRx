package com.example.android.coroutinesvsrx.interactors

import com.cashback.core.base.SingleUseCase
import com.cashback.core.result.Result
import com.cashback.core.result.Failure
import com.example.android.coroutinesvsrx.repository.SearchRepository
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import com.cashback.data.entitys.Deal
import com.cashback.data.entitys.Merchant
import kotlinx.coroutines.experimental.CompletionHandler
import kotlinx.coroutines.experimental.CompletionHandlerException
import java.nio.channels.CompletionHandler

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