package com.omdbapi.moviehunting

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import com.omdbapi.moviehunting.Service.OmdbApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        callApiButton.setOnClickListener{_ ->
            callApi()
        }


        //Observer pattern
        //https://android.jlelse.eu/keddit-part-6-api-retrofit-kotlin-d309074af0
    }

    fun callApi(){
        val restApi = OmdbApi()
        val callResponse = restApi.getMoviesBySearch("Batman")
        val response = callResponse.execute()

        if(response.isSuccessful){
            val listSize = response.body()
            listSizeText.text = "response.isSuccessful"
        }else{
            listSizeText.text = "response.isNotSuccessful"
        }
    }
}
