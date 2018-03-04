package com.omdbapi.moviehunting

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.omdbapi.moviehunting.Model.MovieItem
import com.omdbapi.moviehunting.interfaces.MovieAdapterViewType
import com.omdbapi.moviehunting.interfaces.MovieViewTypeDelegateAdapter
import kotlinx.android.synthetic.main.movie_list_row_item_info.view.*

/**
 * Created by Gozde Kaval on 3/1/2018.
 */
class MovieListInfoAdapter : MovieViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        return MovieListInfoHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: MovieAdapterViewType) {
        (holder as MovieListInfoHolder).bind(item as MovieItem)
    }

    inner class MovieListInfoHolder(parent: ViewGroup?) : RecyclerView.ViewHolder(parent?.inflate(R.layout.movie_list_row_item_info)){
        private val name = itemView.title_text_view
        private val year = itemView.year_text_view
        private val type = itemView.type_text_view
        private val posterImageButton = itemView.posterImage

        fun bind(item: MovieItem) {
            name.text = item.title
            year.text = item.year
            type.text = item.getType()?.name
            posterImageButton.loadPosterImage(item.posterUrl!!)
        }
    }

}