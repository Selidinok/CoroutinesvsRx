package com.example.android.rxjava.search_old


class DealInteractor constructor(
        private val dealRepository: DealRepository
) {
    enum class OrderType { Featured, Ippies, AZ }

    fun getDealsByMerchant(id: String) = dealRepository.getDealsByMerchant(id)

    fun refreshDeals(orderType: OrderType) =
            dealRepository.refreshDeals()
                    .map {
                        when (orderType) {
                            OrderType.Featured -> {
                                it
                            }
                            OrderType.Ippies -> {
                                it.sortedBy { it.cashbackValue.toInt() }
                            }
                            OrderType.AZ -> {
                                it.sortedBy { it.name }
                            }
                        }
                    }

    fun getDeals(orderType: OrderType) =
            dealRepository.getDeals()
                    .map {
                        when (orderType) {
                            OrderType.Featured -> {
                                it
                            }
                            OrderType.Ippies -> {
                                it.sortedBy { it.cashbackValue.toInt() }
                            }
                            OrderType.AZ -> {
                                it.sortedBy { it.name }
                            }
                        }
                    }

    fun getMerchantByDeal(id: String) = dealRepository.getMerchantByDeal(id)

    fun search(keyword: String) = dealRepository.search(keyword)
}