package com.omdbapi.moviehunting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.omdbapi.moviehunting.Fragments.MovieListFragment
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    private var listFragment = MovieListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButtonObserver = getObservableButton()
        searchButtonObserver
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    if(it.length >= 2)
                        listFragment.updateListWith(it)
                    else
                        Log.e("GOZDE","size less then 2")
                }

        addFragment(listFragment)
    }

    fun addFragment(f: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(R.id.frame_layout, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun getObservableButton() : Observable<String>{
        return Observable.create { observer ->
            // 3
            callApiButton.setOnClickListener {

                observer.onNext(searchEditText.text.toString())
            }
        }
    }

    //later
    fun getObservableTextWatcher() : Observable<String>{
        return Observable.create {

            searchEditText.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        }
    }
}
