package com.omdbapi.moviehunting.Model

import android.os.Parcel
import android.os.Parcelable
import com.omdbapi.moviehunting.ViewType
import com.omdbapi.moviehunting.interfaces.MovieAdapterViewType
import com.squareup.moshi.Json

/**
 * Created by Gozde Kaval on 2/26/2018.
 */

data class MovieItem(@Json(name = "Title") val title: String? = null,
                     @Json(name = "Year") val year: String? = null,
                     @Json(name = "imdbID") val imdbId: String? = null,
                     @Json(name = "Type") val type: String? = null,
                     @Json(name = "Poster") var posterJsonUrl: String? = null) : MovieAdapterViewType, Parcelable {

    var posterUrl:String? = ""
    get() {
        return if (!posterJsonUrl.equals("N/A")) posterJsonUrl else field
    }
    /*
    *  set(value) {
        if(value.equals("N/A")) field = ""
    }
    * */
    fun getType() = ItemType.from(type!!)

    override fun getViewType() = ViewType.INFO.id

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(year)
        writeString(imdbId)
        writeString(type)
        writeString(posterUrl)
    }

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR: Parcelable.Creator<MovieItem> = object : Parcelable.Creator<MovieItem> {
            override fun createFromParcel(source: Parcel): MovieItem = MovieItem(source)
            override fun newArray(size: Int): Array<MovieItem?> = arrayOfNulls(size)
        }
    }
}