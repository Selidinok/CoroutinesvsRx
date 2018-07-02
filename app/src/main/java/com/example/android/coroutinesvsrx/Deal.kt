package nl.ippies.entity.domain

data class Deal(
        var id: Int,
        var idDeal: String,
        var idMerchant: String,
        var name: String,
        var description: String,
        var startDate: String,
        var endDate: String,
        var url: String,
        var logoUrl: String,
        var cashbackType: Int,
        var cashbackValue: String
)