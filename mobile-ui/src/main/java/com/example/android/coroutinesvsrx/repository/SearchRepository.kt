package com.example.android.coroutinesvsrx.repository

import com.cashback.core.base.BaseRepository
import com.cashback.data.entitys.toMerchant
import com.cashback.data.entitys.toDeal

/**
 * Created by User on 9:16 30.06.2018.

 */
class SearchRepository : BaseRepository() {
    suspend fun getMerchants(text: String) =
            request(retrofit.searchInMerchants(text), {it.map { it.toMerchant() }}, emptyList())

    suspend fun getDeals(text: String) =
            request(retrofit.searchInDeals(text), {it.map { it.toDeal() }},emptyList())
}