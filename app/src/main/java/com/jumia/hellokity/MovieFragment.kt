package com.jumia.hellokity


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jumia.hellokity.connection.ConfigRetrofit
import com.jumia.hellokity.connection.MovieInterface
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.jetbrains.anko.Android
import org.jetbrains.anko.support.v4.toast
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_movie, container, false)
        getListMovie()

        toast("Hello")

//        val list = Movie.listMovie
//                as ArrayList<MovieModel>
//        val layoutManager =
//            LinearLayoutManager(activity)
//        val adapter =
//            MovieAdapter(list, activity!!.applicationContext)
//
//        rootView.rv_movie.apply {
//            setLayoutManager(layoutManager)
//            setAdapter(adapter)
//        }
        return rootView


    }
    private fun getListMovie(){
     val movieNowPlaying=
            ConfigRetrofit.retrofitConfig()
            .create(MovieInterface::class.java)
            .getListMovieNowPlaying(
                "8ca572e7ad517434320ed69c6d0ed30e"
            )
        movieNowPlaying
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response->
                //Menerima respon yang berhasil

                val list= response.results
                val layoutManager =
                LinearLayoutManager(activity)
                val adapter =
                MovieAdapter(list, activity!!.applicationContext)

                rootView.rv_movie.apply {
                    setLayoutManager(layoutManager)
                    setAdapter(adapter)
                }


            },{
                //Menerima respon yang gagal

            })
    }


}
