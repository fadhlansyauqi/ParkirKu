package org.d3if3049.mobpro1.parkirku.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3049.mobpro1.parkirku.model.BiayaParkir
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


    private const val BASE_URL = "https://raw.githubusercontent.com/" +
            "fadhlansyauqi/json-assesment3-mobpro1/main/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    interface BiayaParkirApiService {
        @GET("biaya-parkir.json")
        suspend fun getBiayaParkir(): List<BiayaParkir>
    }
    object BiayaParkirApi {
        val service: BiayaParkirApiService by lazy {
            retrofit.create(BiayaParkirApiService::class.java)
        }

        fun getBiayaParkirUrl(gambarId: String): String {
            return "$BASE_URL$gambarId.png"
        }

    }

enum class ApiStatus { LOADING, SUCCES, FAILED}
