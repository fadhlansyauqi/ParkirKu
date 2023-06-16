package org.d3if3049.mobpro1.parkirku.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


    private const val BASE_URL = "https://raw.githubusercontent.com/" +
            "fadhlansyauqi/json-assesment3-mobpro1/main/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    interface BiayaParkirApiService {
        @GET("biaya-parkir.json")
        suspend fun getBiayaParkir(): String
    }
    object BiayaParkirApi {
        val service: BiayaParkirApiService by lazy {
            retrofit.create(BiayaParkirApiService::class.java)
        }
    }
