package com.omdbapi.moviehunting.Service

import com.omdbapi.moviehunting.interfaces.OmdbInterface
import com.omdbapi.moviehunting.Model.SearchItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Gozde Kaval on 2/26/2018.
 */
class OmdbApi {

    companion object {
        const val BASE_URL = "http://www.omdbapi.com"
        const val API_KEY = "b5fc6fd8"
    }

    private val omdbInterface: OmdbInterface

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        omdbInterface = retrofit.create(OmdbInterface::class.java)
    }

    fun getMoviesBySearch(keyword:String, pageNo : Int = 1): Call<SearchItem> {
        return omdbInterface.searchWithKeyword(API_KEY,keyword,pageNo)
    }

}