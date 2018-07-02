package com.example.android.coroutinesvsrx

import com.example.android.coroutinesvsrx.interactors.SearchMerchantUseCase
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import ru.gildor.coroutines.retrofit.Result

/**
 * Created by User on 18:33 30.06.2018.

 */
class SearchPresenter(val view: SearchActivity, val boadcast: ConflatedBroadcastChannel<String>) {

    init {
        launch {
            boadcast.consumeEach {
                delay(500)
                if (it.length > 2) startSearch(it)
                else view.showContent(false)
            }

        }
    }

    private fun startSearch(text: String) {
        launch {
            val merchants = SearchMerchantUseCase()
            Result
    }

}