package com.example.android.coroutinesvsrx

import com.google.gson.annotations.SerializedName
import nl.ippies.entity.domain.Merchant
import nl.ippies.entity.server.DealResponse

data class MerchantResponse(
        @SerializedName("id") var id: String,
        @SerializedName("name") var name: String,
        @SerializedName("description") var description: String?,
        @SerializedName("domain") var domain: String?,
        @SerializedName("url") var url: String,
        @SerializedName("logoURL") var logoUrl: String,
        @SerializedName("topBannerURL") var topBannerUrl: String,
        @SerializedName("cashbackType") var cashbackType: Int,
        @SerializedName("cashbackValue") var cashbackValue: Int,
        @SerializedName("isFavorite") var isFavorite: Boolean,
        @SerializedName("offers") var deals: List<DealResponse>
)

data class MerchantByCategoryResponse(
        @SerializedName("id") var id: String,
        @SerializedName("name") var name: String,
        @SerializedName("merchants") var merchants: List<MerchantResponse>
)

fun MerchantResponse.toMerchant() = Merchant(
        0,
        id,
        name,
        description ?: "",
        domain ?: "",
        url,
        logoUrl,
        topBannerUrl,
        cashbackType,
        cashbackValue,
        isFavorite
)

