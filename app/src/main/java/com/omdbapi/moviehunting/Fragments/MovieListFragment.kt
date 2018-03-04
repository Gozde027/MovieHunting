package com.omdbapi.moviehunting.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omdbapi.moviehunting.Model.SearchItem
import com.omdbapi.moviehunting.MovieListAdapter
import com.omdbapi.moviehunting.MovieListShowMoreAdapter
import com.omdbapi.moviehunting.R
import com.omdbapi.moviehunting.Service.OmdbObserver
import com.omdbapi.moviehunting.inflate
import kotlinx.android.synthetic.main.movie_list.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Gozde Kaval on 3/1/2018.
 */
class MovieListFragment : Fragment(), MovieListShowMoreAdapter.onViewClicked {

    private val omdbObserver by lazy { OmdbObserver() }
    private val movieAdapter = MovieListAdapter(this)

    private var movieListSearchOutput : SearchItem? = null

    private var keyword : String = ""
    private var pageNo : Int = 1

    companion object {
        val MOVIE_LIST_KEY = "movieListKEY"
    }

    private val movieList by lazy {
        movie_list_recycler_view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movie_list_recycler_view.setHasFixedSize(true)
        movie_list_recycler_view.layoutManager = LinearLayoutManager(context)
        movieList.adapter = movieAdapter

        if(savedInstanceState != null && savedInstanceState.containsKey(MOVIE_LIST_KEY)){
            movieListSearchOutput = savedInstanceState.get(MOVIE_LIST_KEY) as SearchItem
            movieAdapter.addMovies(movieListSearchOutput!!.movieList)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.movie_list)
    }

    fun updateListWith(keyword: String){
        this.keyword = keyword
        tryApi()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        val movieList = (movieList.adapter as MovieListAdapter).getMovieList()
        if (movieList.isNotEmpty()){
            //TODO try again, does not work good
            outState?.putParcelable(MOVIE_LIST_KEY,movieListSearchOutput?.copy(movieList = movieList))
        }
    }

    fun tryApi(){
        omdbObserver.getMovies(this.keyword,pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            //call req. page
                            movieAdapter.addMovies(it.movieList)
                            setNextRequestPage(it)
                        } ,{
                    Log.i("OUTPUT","throwable"+it.message)
                },{
                    Log.i("OUTPUT","completed")
                }
                )
    }

    override fun onShowInfoViewClicked() {
        tryApi()
    }

    private fun setNextRequestPage(searchOutput : SearchItem){
        val totalSize = searchOutput.totalResult //322
        val currentMovieSize = (movieList.adapter as MovieListAdapter).getCurrentMovieCount() //50

        if(currentMovieSize < totalSize){
            pageNo++
        }else{
            movieAdapter.removeShowMore() //observer for show more
        }
    }
}