@file:Suppress("DEPRECATION")

package com.mohamedwessam.mamacoco.ChildrenSongs

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import com.mohamedwessam.mamacoco.R
import kotlinx.android.synthetic.main.activity_children_songs_preview.*
import java.util.*
import java.util.concurrent.TimeUnit


class ChildrenSongsPreview : AppCompatActivity() {

    private var handler = Handler()

    private var startTime = 0.0
    private var finalTime = 0.0

    var mediaPlayer: MediaPlayer? = null

    private var updateSongTime = object : Runnable {
        override fun run() {
            startTime = mediaPlayer?.currentPosition!!.toDouble()
            txt_playing_duration.text = String.format(
                "%d:%d", TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) -
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                        )
            )

            songs_seekbar.progress = startTime.toInt()
            handler.postDelayed(this, 100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_children_songs_preview)

        //set local language to keep the design save
        val locale = Locale("en")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        //set screen orientation as portrait
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        var pos = intent.getIntExtra("KEY_SONG", 0)
        var song_ID: Int = R.raw.zahab_elel

        when (pos) {
            0 -> {
                song_ID = R.raw.mama_zmanha_gaya
                txt_song_name_preview.text = "ماما زمانها جاية"
            }
            1 -> {
                song_ID = R.raw.zahab_elel
                txt_song_name_preview.text = "ذهب الليل"
            }
            2 -> {
                song_ID = R.raw.gdo_ali
                txt_song_name_preview.text = "جدو علي"
            }
            3 -> {
                song_ID = R.raw.ebre2_shay
                txt_song_name_preview.text = "إبريق الشاي"
            }
        }


        var position = 0
        mediaPlayer = MediaPlayer.create(this, song_ID)

        //set song duration
        finalTime = mediaPlayer?.duration!!.toDouble()
        txt_song_duration.text = String.format(
            "%d:%d",
            TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()))
        )


        btn_play.setOnClickListener {
            mediaPlayer?.seekTo(position)
            mediaPlayer?.start()
            btn_play.visibility = View.GONE
            btn_pause.visibility = View.VISIBLE

            if (oneTimeOnly == 0) {
                songs_seekbar!!.max = finalTime.toInt()
               // oneTimeOnly = 1
            }

            songs_seekbar!!.progress = startTime.toInt()
            handler.postDelayed(updateSongTime, 100)
        }

        btn_pause.setOnClickListener {
            position = mediaPlayer?.currentPosition!!
            mediaPlayer?.pause()
            btn_pause.visibility = View.GONE
            btn_play.visibility = View.VISIBLE
        }

        btn_stop.setOnClickListener {
            mediaPlayer?.stop()
            position = 0
            btn_pause.visibility = View.GONE
            btn_play.visibility = View.VISIBLE
            mediaPlayer = MediaPlayer.create(this, song_ID)
        }

        songs_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, p: Int, fromUser: Boolean) {
                if (fromUser) {
                    position = p
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer?.seekTo(position)
            }
        })

        mediaPlayer?.setOnCompletionListener {
            position = 0
            btn_pause.visibility = View.GONE
            btn_play.visibility = View.VISIBLE
            mediaPlayer = MediaPlayer.create(this, song_ID)
        }

    }


    override fun onDestroy() {
        mediaPlayer?.stop()
        super.onDestroy()
    }

    companion object {
        var oneTimeOnly = 0
    }

}
