@file:Suppress("DEPRECATION")

package com.mohamedwessam.mamacoco.AplaFadelaStories

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.mohamedwessam.mamacoco.R
import kotlinx.android.synthetic.main.activity_main_abla_fadela.*
import java.util.*

class MainAblaFadela : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_abla_fadela)

        //set local language to keep the design save
        val locale = Locale("en")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)


        var childrenFadelaArraylist = ArrayList<ChildrenFadelaRecyclerView>()

        //add items to recyclerView
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "أكبر صح وأكبر غلط"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "المغرور"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "القنفذ الكذاب"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "البنت والأرض"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الأرنب الطماع"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "التجار والعجوز"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الحمار المطرب"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الملك العيان"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الدبة المغرورة"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الساعة الكوكو"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الست العجوزة والثلاث بنات"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "التجار والسلطان"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "المارد المحبوس في قمقم"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الغني اللي كان وكان"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "العصفورة الصغيرة"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "البنت الصغيرة"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الكلب الصغير"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الولد الكسلان"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الكلب المشمشي"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "حسن"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الكتكوت"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "المعدة"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الرجل الغني والرجل الفقير"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الصياد"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الست الطيبة"
            )
        )
        childrenFadelaArraylist.add(
            ChildrenFadelaRecyclerView(
                "الفقير"
            )
        )

        children_fadela_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        var childrenFadelaAdapter = ChildrenFadelaAdapter(childrenFadelaArraylist)
        children_fadela_recyclerview.adapter = childrenFadelaAdapter

        //set onClick for recyclerView
        childrenFadelaAdapter.setOnItemClickListener(object : ChildrenFadelaAdapter.ClickListener {
            override fun onClick(pos: Int, aView: View) {
                var intent = Intent(this@MainAblaFadela, ChildrenFadelaPreview::class.java)
                intent.putExtra("KEY_FADELA", pos)
                startActivity(intent)
            }
        })

    }
}



data class ChildrenFadelaRecyclerView(var mStoryName: String)