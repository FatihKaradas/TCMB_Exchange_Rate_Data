package com.example.tcmbexchangeratedata

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://www.tcmb.gov.tr/"

    val instance: TCMBService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        retrofit.create(TCMBService::class.java)
    }
}
