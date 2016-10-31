package com.nilhcem.devfestnantes.scraper.api

import com.nilhcem.devfestnantes.scraper.model.input.Schedule
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface DevFestApi {

    companion object {
        private val ENDPOINT = "https://raw.githubusercontent.com/GDG-Nantes/devFestSite-2016/master/app/assets/"

        val MOSHI = Moshi.Builder().build()

        val SERVICE = Retrofit.Builder()
                .client(OkHttpClient())
                .baseUrl(ENDPOINT)
                .addConverterFactory(MoshiConverterFactory.create(MOSHI))
                .build()
                .create(DevFestApi::class.java)
    }

    @GET("devfest.json")
    fun getSchedule(): Call<Schedule>
}
