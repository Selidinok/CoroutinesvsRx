package com.example.android.coroutinesvsrx.interactors


/**
 * Created by User on 18:39 30.06.2018.

 */
class SearchUseCase{

    val handler = object : kotlinx.coroutines.experimental.CompletionHandler {
        override fun invoke(cause: Throwable?) {

        }
    }

//    suspend fun run(params: Params) {
//        val merchantsResults = SearchRepository().getMerchants(params.text)
//        if (merchantsResults.isSuccess) {
//            //example: save merchants in DB
////            db.saveAll(merchantsResults)
//        }
//
//        val dealResults = SearchRepository().getDeals(params.text)
//        if (dealResults.isSuccess) {
//            //example: save deals in DB
////            db.saveAll(dealResults)
//            val deal = dealResults
//        }
//
//        val pair = merchantsResults to dealResults
//
//        return Result.Success(pair)
//    }


    data class Params(val text: String)
}