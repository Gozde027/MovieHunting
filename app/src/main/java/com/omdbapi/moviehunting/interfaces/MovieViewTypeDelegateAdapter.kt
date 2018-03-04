package com.omdbapi.moviehunting.interfaces

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Gozde Kaval on 3/1/2018.
 */
interface MovieViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: MovieAdapterViewType)
}