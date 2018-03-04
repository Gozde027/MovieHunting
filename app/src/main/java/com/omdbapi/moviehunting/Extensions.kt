@file:JvmName("ExtensionsUtils")

package com.omdbapi.moviehunting


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadPosterImage(posterUrl:String){
    if(posterUrl.isNotEmpty()){
        Picasso.with(context).load(posterUrl).into(this)
    }else{
        this.visibility = View.GONE
    }
}
