package com.omdbapi.moviehunting

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.omdbapi.moviehunting.Model.SearchOutput
import com.omdbapi.moviehunting.Service.OmdbObserver
import kotlinx.android.synthetic.main.activity_main.*
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val omdbObserver by lazy { OmdbObserver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        callApiButton.setOnClickListener{_ ->
            tryApi(editText.text.toString())
        }

    }

    fun tryApi(keyword : String){

        val subs = omdbObserver.getMovies(keyword)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            printOutput(it)
                        },{}
                )

        /*job = launch(UI) {
            try {
                val output = omdbObserver.getMovies(keyword)
                printOutput(output)
            } catch (e: Throwable) {
                Toast.makeText(applicationContext,"EXCEPTION"+e.message,Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    private fun printOutput(output : SearchOutput){
        output.Search?.forEach {
            Log.e("OUTPUT",it.toString())
        }
        textView.setText("Success")
    }
}
