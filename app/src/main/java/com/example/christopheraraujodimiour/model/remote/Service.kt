package com.example.christopheraraujodimiour.model.remote

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET(Companion.ENDPOINT)
    suspend fun getProfileList(
        @Query(PARAMS) pageNumber: Int = 2
    ): Response<DataResponse>

    companion object {
        const val BASE_URL = "https://reqres.in/"
        const val ENDPOINT = "api/users"
        const val PARAMS = "page"

        fun initRetrofit() =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service::class.java)
    }
}
