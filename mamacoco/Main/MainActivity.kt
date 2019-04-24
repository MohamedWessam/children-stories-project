@file:Suppress("DEPRECATION")

package com.mohamedwessam.mamacoco.Main

import android.app.AlertDialog
import android.content.*
import android.content.res.Configuration
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.mohamedwessam.mamacoco.AplaFadelaStories.MainAblaFadela
import com.mohamedwessam.mamacoco.ChildrenSongs.MainChildrenSongs
import com.mohamedwessam.mamacoco.ChildrenStories.MainChildrenStories
import com.mohamedwessam.mamacoco.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set local language to keep the design save
        val locale = Locale("ar")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        //setting navigation view
        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        mDrawerLayout = findViewById(R.id.main_drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.main_nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Handle navigation view item clicks here.
            when (menuItem.itemId) {
                //rate app
                R.id.item1 -> {
                    try{
                        var uri = Uri.parse("market://details?id$packageName")
                        var i = Intent(Intent.ACTION_VIEW)
                        i.data = uri
                        startActivity(i)
                    }catch (ex: Exception){
                        var uri = Uri.parse("http://play.google.com/store/apps/details?id$packageName")
                        var i = Intent(Intent.ACTION_VIEW)
                        i.data = uri
                        startActivity(i)
                    }
                }
                //share app
                R.id.item2 -> {
                    val sharedText =
                        "حمل تطبيق ماما كوكو، قصص أطفال مصورة وقصص صوتية لأبلة فضيلة، حمل التطبيق من هنا:\n\"http://play.google.com/store/apps/details?id$packageName\""
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, sharedText)
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "subject here")
                    startActivity(Intent.createChooser(shareIntent, "share via"))
                }
                //contact us
                R.id.item3 -> {
                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setTitle("تواصل معنا")
                    builder.setMessage("mohamed.wessam7@gmail.com")
                    builder.setPositiveButton("تراجع"){_,_ ->
                    }
                    builder.setNeutralButton("نسخ"){dialog, which ->
                        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip: ClipData = ClipData.newPlainText("simple text", "mohamed.wessam7@gmail.com")
                        clipboard.primaryClip = clip
                        Snackbar.make(main_drawer_layout," تم نسخ البريد إلى الذاكرة", Snackbar.LENGTH_SHORT).show()
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.colorPrimaryDark))
                    dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(resources.getColor(R.color.colorPrimaryDark))
                }

            }
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here

            true
        }


        //sett on click listeners
        var i: Intent
        btn_children_stories.setOnClickListener {
            i = Intent(this, MainChildrenStories::class.java)
            startActivity(i)
        }
        btn_children_songs.setOnClickListener {
            i = Intent(this, MainChildrenSongs::class.java)
            startActivity(i)
        }
        btn_abla_fadela_stories.setOnClickListener {
            i = Intent(this, MainAblaFadela::class.java)
            startActivity(i)
        }

    }


    //appbar - toolbar button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }


}

