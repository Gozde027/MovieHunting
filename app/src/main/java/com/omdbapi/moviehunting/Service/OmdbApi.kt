package com.omdbapi.moviehunting.Service

import com.omdbapi.moviehunting.Interfaces.OmdbInterface
import com.omdbapi.moviehunting.Model.SearchOutput
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Gozde Kaval on 2/26/2018.
 */
class OmdbApi {

    /* *
    * public static String domain = "http://www.omdbapi.com/
    * */

    private val BASE_URL = "http://www.omdbapi.com"
    private val APIKEY = "b5fc6fd8"
    private val omdbInterface: OmdbInterface

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        omdbInterface = retrofit.create(OmdbInterface::class.java)
    }

    fun getMoviesBySearch(keyword:String): Call<SearchOutput> {
        return omdbInterface.searchWithKeyword(APIKEY,keyword)
    }

}