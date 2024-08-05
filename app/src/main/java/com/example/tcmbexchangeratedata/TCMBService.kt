package com.example.tcmbexchangeratedata

import retrofit2.Call
import retrofit2.http.GET

interface TCMBService {
    @GET("kurlar/today.xml")
    fun getCurrencyRates(): Call<CurrencyResponse>
}
