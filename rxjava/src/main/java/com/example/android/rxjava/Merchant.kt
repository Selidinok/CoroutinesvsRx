package com.example.android.rxjava

data class Merchant(
        var id: Int,
        var idMerchant: String,
        var name: String,
        var description: String,
        var domain: String,
        var url: String,
        var logoUrl: String,
        var topBannerUrl: String,
        var cashbackType: Int,
        var cashbackValue: Int,
        var isFavorite: Boolean
)