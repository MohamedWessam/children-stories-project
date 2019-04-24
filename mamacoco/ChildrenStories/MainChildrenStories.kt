@file:Suppress("DEPRECATION")

package com.mohamedwessam.mamacoco.ChildrenStories

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.mohamedwessam.mamacoco.R
import kotlinx.android.synthetic.main.activity_main_children_stories.*
import java.util.*

class MainChildrenStories : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_children_stories)

        //set local language to keep the design save
        val locale = Locale("en")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)


        var childrenStoriesArraylist = ArrayList<ChildrenStoriesRecyclerView>()

        //add items to recyclerView
        childrenStoriesArraylist.add(ChildrenStoriesRecyclerView(
            "الحمار والبلبل",
            "كان هناك حمار يقف ساعات بالنهار والليل ينهق بصوت عالي وصوته يرن..",
            R.drawable.homar_bolbol1))

        childrenStoriesArraylist.add(ChildrenStoriesRecyclerView(
            "النملة والحمامة",
            "يحكي أن نملة صغيرة خرجت لكي تقوم بجمع الطعام مع اصدقائها ولكن..",
            R.drawable.namla_hamama1))

        childrenStoriesArraylist.add(ChildrenStoriesRecyclerView(
            "الثعلب المكار",
            "كان يا مكان في قديم الزمان في الغابة الكبيرة يعيش أرنب كبير..",
            R.drawable.taalab_makar))

        childrenStoriesArraylist.add(ChildrenStoriesRecyclerView(
            "الأسد والفار",
            "كان الأسد ينام في الغابة بعد أن انتهى من تناول الغداء..",
            R.drawable.asad_far))

        childrenStoriesArraylist.add(ChildrenStoriesRecyclerView(
            "الأرنب والسلحفا",
            "يحكي أنه كان هناك ارنب يعيش في الغابة مع الحيوانات وفي يوم..",
            R.drawable.arnab_solhefa))

        childrenStoriesArraylist.add(ChildrenStoriesRecyclerView(
            "الثعلب يأكل القمر",
            "ذات ليلة مُقمرة، كان الثعلب المكار يطوف متخفياً حول إحدى المزارع..",
            R.drawable.taalab_yakol_amar))

        childrenStoriesArraylist.add(ChildrenStoriesRecyclerView(
            "النملة والصرصور",
            "النملة والصرصور أصدقاء عاشوا معًا في الغابة، كانت النملة تتميز بالنشاط والحيوية..",
            R.drawable.namla_sarsour))

        childrenStoriesArraylist.add(ChildrenStoriesRecyclerView(
            "الصياد القنوع",
            "حامد وعلي أخوين يعملون بصيد السمك، كان حامد يمتلك قارب صغير..",
            R.drawable.sayad_kanooa))

        children_stories_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        var childrenStoriesAdapter = ChildrenStoriesAdapter(childrenStoriesArraylist)
        children_stories_recyclerview.adapter = childrenStoriesAdapter

        //set onClick for recyclerView
        childrenStoriesAdapter.setOnItemClickListener(object : ChildrenStoriesAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                var intent = Intent(this@MainChildrenStories, ChildrenStoryPreview::class.java)
                intent.putExtra("key", pos)
                startActivity(intent)
            }
        })

    }
}
