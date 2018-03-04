package com.omdbapi.moviehunting.Model

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

/**
 * Created by Gozde Kaval on 2/26/2018.
 */
data class SearchItem(
        @Json(name = "Search") val movieList: List<MovieItem>,
        @Json(name = "totalResults") val totalResult: Int,
        @Json(name = "Response") val response: String) : Parcelable {

    constructor(source: Parcel) : this(
            source.createTypedArrayList(MovieItem.CREATOR),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(movieList)
        writeInt(totalResult)
        writeString(response)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SearchItem> = object : Parcelable.Creator<SearchItem> {
            override fun createFromParcel(source: Parcel): SearchItem = SearchItem(source)
            override fun newArray(size: Int): Array<SearchItem?> = arrayOfNulls(size)
        }
    }
}