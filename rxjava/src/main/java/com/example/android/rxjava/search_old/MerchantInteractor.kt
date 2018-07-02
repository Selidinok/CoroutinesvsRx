package com.example.android.rxjava.search_old

import com.example.android.rxjava.Merchant
import nl.ippies.entity.domain.Merchant
import nl.ippies.extension.ViewUtils
import nl.ippies.extension.upperFirstLetter
import javax.inject.Inject

class MerchantInteractor @Inject constructor(
        private val merchantRepository: MerchantRepository
) {

    fun getMerchant(id: String) = merchantRepository.getMerchant(id)

    fun getMerchantCache(id: String) = merchantRepository.getMerchantFromCache(id)

    fun getMerchantForSure(id: String) = merchantRepository.getMerchantForSure(id)

    fun getMerchants() = merchantRepository.getMerchants()
            .map {
                it.forEach { it.name = ViewUtils.fromHtml((it.name.trim().upperFirstLetter())) }
                return@map it.sortedBy { it.name }
            }

    fun refreshMerchants() = merchantRepository.refreshMerchants()
            .map {
                it.forEach { it.name = ViewUtils.fromHtml((it.name.trim().upperFirstLetter())) }
                return@map it.sortedBy { it.name }
            }

    fun getMerchantsByCategory(id: String) = merchantRepository.getMerchantsByCategory(id)
            .map {
                it.forEach { it.name = ViewUtils.fromHtml((it.name.trim().upperFirstLetter())) }
                return@map it.sortedBy { it.name }
            }

    fun addToFavorites(merchant: Merchant) = merchantRepository.addToFavorites(merchant)

    fun removeFromFavorites(merchant: Merchant) = merchantRepository.removeFromFavorites(merchant)

    fun getFavorites() = merchantRepository.getFavorites()

    fun search(keyword: String) = merchantRepository.searchInMerchant(keyword)
}