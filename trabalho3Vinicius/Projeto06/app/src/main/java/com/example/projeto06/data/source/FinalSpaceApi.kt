package com.example.projeto06.data.source

import com.example.projeto06.data.domain.Character
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://finalspaceapi.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FinalSpaceApiService {
    @GET("api/v0/character")
    suspend fun getChars(): List<SourceCharacter>
}

object FinalSpaceApi {
    val retrofitService: FinalSpaceApiService by lazy{
        retrofit.create(FinalSpaceApiService::class.java)
    }
}