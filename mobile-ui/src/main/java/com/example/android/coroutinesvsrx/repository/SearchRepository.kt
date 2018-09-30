package com.example.android.coroutinesvsrx.repository

import com.domain.core.base.BaseRemoteRepository
import com.domain.data.entitys.toMerchant
import com.domain.data.entitys.toDeal

/**
 * Created by User on 9:16 30.06.2018.

 */
class SearchRepository : BaseRemoteRepository() {
    suspend fun getMerchants(text: String) =
            request(retrofit.searchInMerchants(text), {it.map { it.toMerchant() }}, emptyList())

    suspend fun getDeals(text: String) =
            request(retrofit.searchInDeals(text), {it.map { it.toDeal() }},emptyList())
}