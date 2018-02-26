package com.omdbapi.moviehunting.Model

/**
 * Created by Gozde Kaval on 2/26/2018.
 */

data class Movie (val Title: String? = null,
                  val Year: String? = null,
                  val imdbID: String? = null,
                  val Type: String? = null,
                  val Poster: String? = null){
}