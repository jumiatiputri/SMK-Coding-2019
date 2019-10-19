package com.jumia.hellokity.connection

import com.jumia.hellokity.model.ResponseMovieModel
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import java.util.*

interface MovieInterface {
    @GET("movie/now_playing")
    fun getListMovieNowPlaying(
        @Query("api_key")
        apiKey:String
    ): Observable<ResponseMovieModel>
}