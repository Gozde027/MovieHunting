package com.omdbapi.moviehunting

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.omdbapi.moviehunting.Model.SearchOutput
import com.omdbapi.moviehunting.Service.OmdbObserver
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val omdbObserver by lazy { OmdbObserver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButtonObserver = getObservableButton()
        searchButtonObserver
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { textView.setText("BEKLENİYOR...") }
                .subscribe{
                    tryApi(it)
                }
    }

    fun getObservableButton() : Observable<String>{
        return Observable.create { observer ->
            // 3
            callApiButton.setOnClickListener {
                // 4
                observer.onNext(editText.text.toString())
            }
        }
    }

    fun tryApi(keyword : String){

        omdbObserver.getMovies(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                     {
                         printOutput(it)
                     } ,{
                        Log.i("OUTPUT","throwable"+it.message)
                     },{
                        Log.i("OUTPUT","completed")
                     }
                )
    }

    private fun printOutput(output : SearchOutput){
        output.Search?.forEach {
            Log.i("OUTPUT","data : " + it.toString())
        }
        textView.setText("DONE...")
    }
}
