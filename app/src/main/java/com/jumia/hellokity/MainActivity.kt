package com.jumia.hellokity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    btn_list_movie.onClick {
//        val intent =
//            Intent (this@MainActivity, ListMovieactivity::class.java)
       startActivity(intentFor<ListMovieactivity>())

    }
        btn_list_profil_movie.onClick {
            startActivity(intentFor<profil_Activity>())
        }

        btn_list_favorit_movie.onClick {
            startActivity(intentFor<favorit_Activity>())
        }

    }
}
