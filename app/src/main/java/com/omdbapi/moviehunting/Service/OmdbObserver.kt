package com.omdbapi.moviehunting.Service

import com.omdbapi.moviehunting.Model.SearchItem
import rx.Observable

/**
 * Created by Gozde Kaval on 2/27/2018.
 */
class OmdbObserver {

    fun getMovies(keyword: String, pageNo: Int = 1) : Observable<SearchItem>{

        return Observable.create {
            subscriber ->

            val restApi = OmdbApi()
            val callResponse = restApi.getMoviesBySearch(keyword,pageNo)
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