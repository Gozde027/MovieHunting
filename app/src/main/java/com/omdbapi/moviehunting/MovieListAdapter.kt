package com.omdbapi.moviehunting

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.omdbapi.moviehunting.Model.MovieItem
import com.omdbapi.moviehunting.interfaces.MovieAdapterViewType
import com.omdbapi.moviehunting.interfaces.MovieViewTypeDelegateAdapter

/**
 * Created by Gozde Kaval on 3/1/2018.
 */
class MovieListAdapter(showMoreListener : MovieListShowMoreAdapter.onViewClicked) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items : ArrayList<MovieAdapterViewType>
    private var delegateAdapters = SparseArrayCompat<MovieViewTypeDelegateAdapter>()

    private var showMoreItem = object : MovieAdapterViewType{
        override fun getViewType() = ViewType.SHOW_MORE.id
    }

    fun getMovieList() : List<MovieItem>{
        return items
                .filter {
                    it.getViewType() == ViewType.INFO.id
                    }
                .map { it as MovieItem }
    }

    fun getCurrentMovieCount() : Int{
        return getMovieList().size
    }

    init {
        delegateAdapters.put(ViewType.SHOW_MORE.id,MovieListShowMoreAdapter(showMoreListener))
        delegateAdapters.put(ViewType.INFO.id,MovieListInfoAdapter())

        items = ArrayList()
        items.add(showMoreItem)
    }

    fun addMovies(movieList : List<MovieItem>){

        val lastItem = items.size - 1
        items.removeAt(lastItem)
        items.addAll(movieList)
        items.add(showMoreItem)
        notifyDataSetChanged()
    }

    fun removeShowMore(){
        val lastItem = items.size - 1
        items.removeAt(lastItem)
        notifyItemRemoved(lastItem)
    }

    override fun getItemViewType(position: Int) = items[position].getViewType()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        delegateAdapters.get(this.items[position].getViewType()).onBindViewHolder(holder,this.items[position])
    }
}