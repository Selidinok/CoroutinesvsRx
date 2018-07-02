package nl.ippies.presentation.search

import com.arellomobile.mvp.InjectViewState
import com.example.android.rxjava.Merchant
import com.example.android.rxjava.Deal
import com.example.android.rxjava.search_old.SearchActivityFragment
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import nl.ippies.Screens
import nl.ippies.entity.domain.Deal
import nl.ippies.entity.domain.Merchant
import nl.ippies.model.interactor.deal.DealInteractor
import nl.ippies.model.interactor.merchant.MerchantInteractor
import nl.ippies.model.system.SchedulersProvider
import nl.ippies.model.system.flow.FlowRouter
import nl.ippies.presentation.global.BasePresenter
import nl.ippies.presentation.global.ErrorHandler
import java.util.function.BiFunction
import javax.inject.Inject

class SearchPresenter(val broadcastChannel: ConflatedBroadcastChannel<String>, val view: SearchActivityFragment) {

//    private val stateRelay = PublishRelay.create<String>()
//    private val state: Observable<String> = stateRelay

//    override fun onFirstViewAttach() {
//        super.onFirstViewAttach()
//
//        state.debounce(1000, TimeUnit.MILLISECONDS)
//                .observeOn(schedulers.ui())
//                .subscribe {
//                    if (it.length > 2) startSearch(it)
//                    else viewState.showContent(false)
//                }
//                .connect()
//    }

    fun search(keyword: String) {
        launch {
            broadcastChannel.consumeEach {
                delay(500)
                if (it.length > 2) startSearch(it)
                else view.showContent(false)
            }
        }
    }

    private fun startSearch(keyword: String) = merchantInteractor.search(keyword)
            .observeOn(schedulers.io())
            .zipWith(
                    dealInteractor.search(keyword),
                    BiFunction { merchants: List<Merchant>, deals: List<Deal> ->
                        Pair(merchants, deals)
                    }
            )
            .observeOn(schedulers.ui())
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate {
                viewState.showProgress(false)
                viewState.showContent(true)
            }
            .subscribe(
                    {
                        val merchants = it.first
                        val deals = it.second
                        if (merchants.isEmpty() && deals.isEmpty()) {
                            viewState.showEmptyData(true)
                        } else {
                            viewState.showMerchants(merchants)
                            viewState.showDeals(deals)
                            viewState.showEmptyData(false)
                        }
                    },
                    { showError(it) }
            ).connect()

    private fun showError(error: Throwable) {
        viewState.showEmptyData(true)
        errorHandler.proceed(error, { viewState.showMessage(it) })
    }

    fun onShareClicked(deal: Deal) = dealInteractor.getMerchantByDeal(deal.idMerchant)
            .subscribe(
                    {
                        viewState.share(deal.url, it.name)
                    },
                    {
                        errorHandler.proceed(it, { viewState.showMessage(it) })
                    }
            )

    fun openMerchant(idMerchant: String) = router.startFlow(Screens.MERCHANT_FLOW, idMerchant)
    fun openBrowser(deal: Deal) = router.startFlow(Screens.MERCHANT_BROWSER_FLOW, Pair(deal.idMerchant, deal.url))

    fun onBackPressed() = router.exit()
}