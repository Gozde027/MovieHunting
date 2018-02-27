package com.omdbapi.moviehunting.Service

import com.omdbapi.moviehunting.Model.SearchOutput
import rx.Observable

/**
 * Created by Gozde Kaval on 2/27/2018.
 */
class OmdbObserver {

    fun getMovies(keyword: String) : Observable<SearchOutput>{

        return Observable.create {
            subscriber ->

            val restApi = OmdbApi()
            val callResponse = restApi.getMoviesBySearch(keyword)
            val response = callResponse.execute()

            if(response.isSuccessful){
                val searchOutput = response.body()!!
                subscriber.onNext(searchOutput)
                subscriber.onCompleted()
            }else{
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}