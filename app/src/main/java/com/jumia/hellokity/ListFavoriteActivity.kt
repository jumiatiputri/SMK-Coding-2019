package com.jumia.hellokity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jumia.hellokity.database.DatabaseContract
import com.jumia.hellokity.database.database
import com.jumia.hellokity.model.ResultsItem
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListFavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_favorite)
        selectFavoriteFromSqlite()
    }
    private fun selectFavoriteFromSqlite(){
        database.use{
            val selectData=select(ResultsItem.TABLE_FAVORITE)
            val list: MutableList<DatabaseContract> =
            selectData.parseList(classParser<DatabaseContract>()) as MutableList
            val arrayListMovie=list as ArrayList<DatabaseContract>
            Log.d("LISTFAVORITE",arrayListMovie.toString())
        }
    }
}
