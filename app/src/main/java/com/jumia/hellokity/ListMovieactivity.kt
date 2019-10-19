package com.jumia.hellokity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list_movieactivity.*

class ListMovieactivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movieactivity)

       viewpager.adapter =
            TabLayoutAdapter(supportFragmentManager,this)
        tablayout.setupWithViewPager(viewpager)
    }
}
