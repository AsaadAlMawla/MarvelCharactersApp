package com.mawla.marvelcharacterkotlinapp.WebservicesPackage

import com.mawla.marvelcharacterkotlinapp.Dtos.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPIInterface {

    @GET("/v1/public/characters?")
    fun getCharactersList(@Query("ts") ts: Long, @Query("apikey") apikey: String?, @Query("hash") hash: String?): Call<MarvelCharacterDataWrapper?>?

    @GET("/v1/public/characters/{characterId}?")
    fun getCharacterDetails(@Path("characterId") characterId: Int?, @Query("ts") ts: Long, @Query("apikey") apikey: String?, @Query("hash") hash: String?): Call<MarvelCharacterDataWrapper?>?

    @GET("/v1/public/characters/{characterId}/comics?")
    fun getCharacterComics(@Path("characterId") characterId: Int?, @Query("ts") ts: Long, @Query("apikey") apikey: String?, @Query("hash") hash: String?): Call<MarvelComicDataWrapper?>?

    @GET("/v1/public/characters/{characterId}/events?")
    fun getCharacterEvents(@Path("characterId") characterId: Int?, @Query("ts") ts: Long, @Query("apikey") apikey: String?, @Query("hash") hash: String?): Call<MarvelEventDataWrapper?>?

    @GET("/v1/public/characters/{characterId}/stories?")
    fun getCharacterStories(@Path("characterId") characterId: Int?, @Query("ts") ts: Long, @Query("apikey") apikey: String?, @Query("hash") hash: String?): Call<MarvelStoryDataWrapper?>?

    @GET("/v1/public/characters/{characterId}/series?")
    fun getCharacterSeries(@Path("characterId") characterId: Int?, @Query("ts") ts: Long, @Query("apikey") apikey: String?, @Query("hash") hash: String?): Call<MarvelSeriesDataWrapper?>?


}