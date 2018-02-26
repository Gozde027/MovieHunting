package com.omdbapi.moviehunting.Interfaces

import com.omdbapi.moviehunting.Model.Movie
import com.omdbapi.moviehunting.Model.SearchOutput
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
                          @Query("s") s: String) : Call<SearchOutput>

    @GET("/")
    fun searchWithTitle(@Query("apikey") apikey: String,//"b5fc6fd8"
                          @Query("t") t: String) : Call<SearchOutput>

}