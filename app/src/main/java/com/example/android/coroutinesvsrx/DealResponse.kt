package nl.ippies.entity.server

import com.google.gson.annotations.SerializedName

data class DealResponse(
        @SerializedName("id") var id: String,
        @SerializedName("storeID") var idMerchant: String,
        @SerializedName("name") var name: String,
        @SerializedName("description") var description: String,
        @SerializedName("startDate") var startDate: String,
        @SerializedName("endDate") var endDate: String,
        @SerializedName("deepLinkURL") var url: String,
        @SerializedName("logoURL") var logoUrl: String,
        @SerializedName("cashbackType") var cashbackType: Int,
        @SerializedName("cashbackValue") var cashbackValue: String
)