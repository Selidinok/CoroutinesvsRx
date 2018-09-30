package com.example.android.coroutinesvsrx

import com.domain.data.entitys.DealResponse
import com.domain.data.entitys.MerchantResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IppiesApi {
    companion object {
        const val API_PATH = "https://api.github.com"
    }

//    ////// Access  //////
//
//    @FormUrlEncoded
//    @POST("$API_PATH/grant")
//    fun accessToken(
//            @Field("client_id") clientId: String,
//            @Field("client_secret") clientSecret: String,
//            @Field("grant_type") grantType: String
//    ): Single<AccessResponse>
//
//    ///// Profile //////
//
//    @FormUrlEncoded
//    @POST("$API_PATH/user/sign-in/social")
//    fun signInViaSocial(
//            @Field("socnet") socnet: String,
//            @Field("socnetToken") socnetToken: String
//    ): Single<AuthResponse>
//
//    @FormUrlEncoded
//    @POST("$API_PATH/user/sign-up/social")
//    fun signUpViaSocial(
//            @Field("socnet") socnet: String,
//            @Field("socnetToken") socnetToken: String
//    ): Single<AuthResponse>
//
//    @POST("$API_PATH/user/sign-up")
//    fun signUp(
//            @Body body: Map<String, String>
//    ): Single<AuthResponse>
//
//    @FormUrlEncoded
//    @POST("$API_PATH/user/sign-in")
//    fun signIn(
//            @Field("email") email: String,
//            @Field("password") password: String
//    ): Single<AuthResponse>
//
//    @GET("$API_PATH/user/profile")
//    fun profile(): Single<ProfileResponse>
//
//    @FormUrlEncoded
//    @POST("$API_PATH/user/recovery")
//    fun recoveryPassword(
//            @Field("email") email: String
//    ): Single<AuthResponse>
//
//    ///// Categories //////
//
//    @GET("$API_PATH/category/all")
//    fun categories(): Single<List<CategoryResponse>>
//
//    @GET("$API_PATH/category/{id}")
//    fun subCategories(
//            @Path("id") id: String
//    ): Single<List<CategoryResponse>>
//
//    ///// Carousel //////
//
//    @GET("$API_PATH/carousel/all")
//    fun carousel(): Single<List<CarouselResponse>>

    ///// Merchants //////

//    @GET("$API_PATH/merchant/all")
//    fun merchants(): Single<List<MerchantResponse>>
//
//    @GET("$API_PATH/merchant/{id}")
//    fun merchant(
//            @Path("id") id: String
//    ): Single<MerchantResponse>
//
//    @GET("$API_PATH/category/subcategory/{id}")
//    fun merchantsByCategory(
//            @Path("id") id: String
//    ): Single<MerchantByCategoryResponse>
//
//    @GET("$API_PATH/merchant/favorite")
//    fun favoriteMerchants(): Single<List<MerchantResponse>>
//
//    @POST("$API_PATH/merchant/favorite/{id}")
//    fun addMerchantToFavorites(
//            @Path("id") id: String
//    ): Completable
//
//    @DELETE("$API_PATH/merchant/favorite/{id}")
//    fun removeMerchantFromFavorites(
//            @Path("id") id: String
//    ): Completable

    @FormUrlEncoded
    @POST("$API_PATH/search/merchant")
    fun searchInMerchants(
            @Field("searchTerm") keyword: String
    ): Call<List<MerchantResponse>>

    ////// Deals //////

//    @GET("$API_PATH/deals/all")
//    fun deals(): Single<List<DealResponse>>
//
//    @GET("$API_PATH/deals/featured")
//    fun dealsFeatured(): Single<List<DealResponse>>
//
//    @GET("$API_PATH/deals/{id}")
//    fun dealsByMerchant(
//            @Path("id") id: String
//    ): Single<List<DealResponse>>

    @FormUrlEncoded
    @POST("$API_PATH/search/offer")
    fun searchInDeals(
            @Field("searchTerm") keyword: String
    ): Call<List<DealResponse>>


//    ////// Transactions /////
//    @GET("$API_PATH/user/transactions")
//    fun transactions(): Single<TransactionResponse>
//
//    ///// Device token /////
//    @FormUrlEncoded
//    @POST("$API_PATH/user/profile/deviceToken")
//    fun updateDeviceToken(
//            @Field("deviceToken") deviceToken: String
//    ): Completable
//
//    @DELETE("$API_PATH/user/profile/deviceToken/{deviceToken}")
//    fun deleteDeviceToken(
//            @Path("deviceToken") deviceToken: String
//    ): Completable
}