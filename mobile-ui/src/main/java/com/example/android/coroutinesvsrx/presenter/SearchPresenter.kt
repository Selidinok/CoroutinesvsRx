package com.example.android.coroutinesvsrx.presenter

import com.domain.core.result.Result
import com.example.android.coroutinesvsrx.SearchActivity
import com.domain.core.result.Failure
import com.example.android.coroutinesvsrx.interactors.SearchUseCase
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import com.domain.data.entitys.Deal
import com.domain.data.entitys.Merchant

/**
 * Created by User on 18:33 30.06.2018.

 */
class SearchPresenter(val view: SearchActivity, val boadcast: ConflatedBroadcastChannel<String>) : BasePresenter() {
    private val search = SearchUseCase()

    init {
        launch(context) {
            boadcast.consumeEach {
                delay(500)
                if (it.length > 2) startSearch(it)
                else view.showContent(false)
            }
        }
    }

    private fun startSearch(text: String) {
        view.showProgress(true)
        search(SearchUseCase.Params(text)) { onResultSearch(it) }
        view.showProgress(false)
    }

    private fun onResultSearch(pair: Pair<Result<Failure, List<Merchant>>, Result<Failure, List<Deal>>>) {
        val merchants = pair.first
        merchants.either({ view.showMerchantError(it) }, { view.showMerchants(it) })

        val deals = pair.second
        deals.either({ view.showDealsError(it) }, { view.showDeals(it) })
    }

    override fun handleError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}