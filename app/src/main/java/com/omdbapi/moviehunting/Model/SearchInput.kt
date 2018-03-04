package com.omdbapi.moviehunting.Model

/**
 * Created by Gozde Kaval on 3/4/2018.
 */

/*
* Parameter	Required	Valid options	Default Value	Description
s***		                        Movie title to search for.
type	 movie, series, episode	    Type of result to return.
y	   	 <empty>                    Year of release.
r	     json, xml		            The data type to return.
page 	 1	                        Page number to return.
callback <empty>	                JSONP callback name.
v        1	                        API version (reserved for future use).
* */
data class SearchInput(val pageNo:Int, val keyword:String) {

    //optional values will be added with builder !!
}