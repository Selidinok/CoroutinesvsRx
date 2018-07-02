package com.example.android.coroutinesvsrx.interactors

import com.example.android.coroutinesvsrx.MerchantResponse
import com.example.android.coroutinesvsrx.interactors.SearchMerchantUseCase.Params
import com.example.android.coroutinesvsrx.repository.SearchRepository
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import nl.ippies.entity.domain.Merchant
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.getOrDefault

/**
 * Created by User on 18:39 30.06.2018.

 */
class SearchMerchantUseCase : UseCase<List<Merchant>, Params>() {

    override fun run(params: Params): List<Merchant> {
        launch(UI) {
            val merchantsResults = async(CommonPool) { SearchRepository().getMerchants(params.text) }
            val dealResults = async(CommonPool) { SearchRepository().getDeals(params.text) }

            val (merchants, deals) = Pair(merchantsResults.await(), dealResults.await())
            (merchants as Result.Ok).
        }
    }

    data class Params(val text: String)
}