package com.jumia.hellokity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.jumia.hellokity.database.database
import com.jumia.hellokity.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie=
            intent.getParcelableExtra<ResultsItem>("movie")
        tv_title_movie.text=movie.title
        tv_rating_movie.text="Rating : ${movie.popularity}"
        tv_description_movie.text=movie.overview
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movie.posterPath).into(iv_poster_movie)

        btn_list_favorit_movie.onClick{
            //checkMovieFavorite(movie)
            addMovieToFavorite(movie)
        }
    }
    private fun checkMovieFavorite(movie:ResultsItem?){

    }
    private fun addMovieToFavorite(movie:ResultsItem?){
        database.use {
            insert(ResultsItem.TABLE_FAVORITE,
                ResultsItem.COLUMN_TITLE to movie?.title,
                ResultsItem.COLUMN_POSTERPATH to movie?.posterPath,
                ResultsItem.COLUMN_RATING to movie?.popularity,
                ResultsItem.COLUMN_DESCRIPTION to movie?.overview
                )
            toast("Berhasil ditambah ke favorit")
        }
    }
}
