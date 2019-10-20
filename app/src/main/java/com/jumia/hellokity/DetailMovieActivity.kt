package com.jumia.hellokity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.jumia.hellokity.database.DatabaseContract
import com.jumia.hellokity.database.database
import com.jumia.hellokity.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class DetailMovieActivity : AppCompatActivity() {
    var isMovieFavorite:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie=
            intent.getParcelableExtra<ResultsItem>("movie")
        tv_title_movie.text=movie.title
        tv_rating_movie.text="Rating : ${movie.popularity}"
        tv_description_movie.text=movie.overview
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movie.posterPath).into(iv_poster_movie)

        //Cek movie sudah terdaftar di favorite atau belum
        checkMovieFavorite(movie)
        btn_list_favorit_movie.onClick{
           if(isMovieFavorite){
               deleteMovieFromFavorite(movie)
           }
            else{
               addMovieToFavorite(movie)
           }

        }
    }
    private fun checkMovieFavorite(movie:ResultsItem?){
        database.use{
            var isFavorite=select(ResultsItem.TABLE_FAVORITE,ResultsItem.COLUMN_TITLE)
                .whereArgs(ResultsItem.COLUMN_TITLE+" = {title}",
                    "title" to movie?.title.toString())
            val dataMovie:DatabaseContract?
            =isFavorite.parseOpt(classParser<DatabaseContract>())

          Log.d("FAVORITEMOVIE",dataMovie.toString())
            if(dataMovie!= null){
                isMovieFavorite=true
                btn_list_favorit_movie.text="Hapus Favorite"
            }
            else
            {
                isMovieFavorite=false
                btn_list_favorit_movie.text="Tambah Favorite"
            }
        }
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
            isMovieFavorite=true
            btn_list_favorit_movie.text="Hapus Favorite"
        }
    }
    private fun deleteMovieFromFavorite(movie:ResultsItem){
        database.use {
            delete(
                ResultsItem.TABLE_FAVORITE, "${ResultsItem.COLUMN_TITLE}={title}",
                "title" to movie?.title.toString()
            )
            toast("Movie dihapus dari daftar movie")
            isMovieFavorite = false
            btn_list_favorit_movie.text = "Tambah Favorite"
        }
    }
}
