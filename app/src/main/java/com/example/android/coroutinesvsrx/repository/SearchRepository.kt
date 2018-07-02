package com.example.android.coroutinesvsrx.repository

import com.example.android.coroutinesvsrx.IppiesApi
import retrofit2.Call
import retrofit2.Retrofit
import ru.gildor.coroutines.retrofit.awaitResult

/**
 * Created by User on 9:16 30.06.2018.

 */
class SearchRepository : BaseRepository(){
    suspend fun getMerchants(text: String) = request(retrofit.searchInMerchants(text))
    suspend fun getDeals(text: String) = request(retrofit.searchInDeals(text))
}