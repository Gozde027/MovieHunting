package com.omdbapi.moviehunting.interfaces

import com.omdbapi.moviehunting.Model.SearchItem
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Created by Gozde Kaval on 2/26/2018.
 */
interface OmdbInterface {

//http://www.omdbapi.com/?t=batman
    @GET("/")
    fun searchWithKeyword(@Query("apikey") apikey: String,
                                    @Query("s") s: String,
                                    @Query("page") pageNumber: Int = 1) : Call<SearchItem>

    @GET("/")
    fun searchWithTitle(@Query("apikey") apikey: String,//"b5fc6fd8"
                        @Query("t") t: String,
                        @Query("page") pageNumber: Int = 1) : Call<SearchItem>

}