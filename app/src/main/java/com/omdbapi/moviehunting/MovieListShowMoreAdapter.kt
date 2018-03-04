package com.omdbapi.moviehunting

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.omdbapi.moviehunting.interfaces.MovieAdapterViewType
import com.omdbapi.moviehunting.interfaces.MovieViewTypeDelegateAdapter

/**
 * Created by Gozde Kaval on 3/1/2018.
 */
class MovieListShowMoreAdapter(val onClickInterface : onViewClicked) : MovieViewTypeDelegateAdapter {

    interface onViewClicked {
        fun onShowInfoViewClicked()
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return MovieListShowMoreHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: MovieAdapterViewType) {
        (holder as MovieListShowMoreHolder).bind()
    }

    inner class MovieListShowMoreHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(
            parent?.inflate(R.layout.movie_list_row_item_show_more)){

        fun bind(){
            itemView.setOnClickListener {
                onClickInterface.onShowInfoViewClicked()
            }
        }
    }

}