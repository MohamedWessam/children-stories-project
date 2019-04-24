@file:Suppress("DEPRECATION")

package com.mohamedwessam.mamacoco.ChildrenSongs

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.mohamedwessam.mamacoco.R
import kotlinx.android.synthetic.main.activity_main_children_songs.*
import java.util.*

class MainChildrenSongs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_children_songs)

        //set local language to keep the design save
        val locale = Locale("en")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)


        var childrenSongsArraylist = ArrayList<ChildrenSongsRecyclerView>()

        //add items to recyclerView
        childrenSongsArraylist.add(
            ChildrenSongsRecyclerView(
                "ماما زمانها جاية"
            )
        )

        childrenSongsArraylist.add(
            ChildrenSongsRecyclerView(
                "ذهب الليل"
            )
        )

        childrenSongsArraylist.add(
            ChildrenSongsRecyclerView(
                "جدو علي"
            )
        )

        childrenSongsArraylist.add(
            ChildrenSongsRecyclerView(
                "إبريق الشاي"
            )
        )


        children_songs_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        var childrenSongsAdapter = ChildrenSongsAdapter(childrenSongsArraylist)
        children_songs_recyclerview.adapter = childrenSongsAdapter

        //set onClick for recyclerView
        childrenSongsAdapter.setOnItemClickListener(object : ChildrenSongsAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                var intent = Intent(this@MainChildrenSongs, ChildrenSongsPreview::class.java)
                intent.putExtra("KEY_SONG", pos)
                startActivity(intent)
            }
        })

    }
}


data class ChildrenSongsRecyclerView(var mSongName: String)
