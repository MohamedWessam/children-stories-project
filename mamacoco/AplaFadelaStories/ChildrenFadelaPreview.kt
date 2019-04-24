package com.mohamedwessam.mamacoco.AplaFadelaStories

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import com.mohamedwessam.mamacoco.R
import kotlinx.android.synthetic.main.activity_children_fadela_preview.*
import java.util.*
import java.util.concurrent.TimeUnit

@Suppress("DEPRECATION")
class ChildrenFadelaPreview : AppCompatActivity() {

    private var handler = Handler()

    private var startTime = 0.0
    private var finalTime = 0.0

    var mediaPlayer: MediaPlayer? = null

    private var updateSongTime = object : Runnable {
        override fun run() {
            startTime = mediaPlayer?.currentPosition!!.toDouble()
            txt_playing_duration_fadela.text = String.format(
                "%d:%d", TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) -
                        TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                        )
            )

            fadela_seekbar.progress = startTime.toInt()
            handler.postDelayed(this, 100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_children_fadela_preview)

        //set local language to keep the design save
        val locale = Locale("en")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        //set screen orientation as portrait
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        var pos = intent.getIntExtra("KEY_FADELA", 0)
        var audio_ID: Int = R.raw.zahab_elel

        when (pos) {
            0 -> {
                audio_ID = R.raw.akbar_sah_akbar_ghalat
                txt_fadela_name_preview.text = "أكبر صح وأكبر غلط"
            }
            1 -> {
                audio_ID = R.raw.maghror
                txt_fadela_name_preview.text = "المغرور"
            }
            2 -> {
                audio_ID = R.raw.onfez_kadab
                txt_fadela_name_preview.text = "القنفذ الكذاب"
            }
            3 -> {
                audio_ID = R.raw.bent_we_ard
                txt_fadela_name_preview.text = "البنت والأرض"
            }
            4 -> {
                audio_ID = R.raw.arnab_tamaa
                txt_fadela_name_preview.text = "الأرنب الطماع"
            }
            5 -> {
                audio_ID = R.raw.togar_we_agoz
                txt_fadela_name_preview.text = "التجار والعجوز"
            }
            6 -> {
                audio_ID = R.raw.homar_motreb
                txt_fadela_name_preview.text = "الحمار المطرب"
            }
            7 -> {
                audio_ID = R.raw.malk_ayan
                txt_fadela_name_preview.text = "الملك العيان"
            }
            8 -> {
                audio_ID = R.raw.doba_maghrora
                txt_fadela_name_preview.text = "الدبة المغرورة"
            }
            9 -> {
                audio_ID = R.raw.sa3a_koko
                txt_fadela_name_preview.text = "الساعة الكوكو"
            }
            10 -> {
                audio_ID = R.raw.set_agoza
                txt_fadela_name_preview.text = "الست العجوزة والثلاث بنات"
            }
            11 -> {
                audio_ID = R.raw.tager_we_soltan
                txt_fadela_name_preview.text = "التجار والسلطان"
            }
            12 -> {
                audio_ID = R.raw.mared_mahbos
                txt_fadela_name_preview.text = "المارد المحبوس في قمقم"
            }
            13 -> {
                audio_ID = R.raw.ghany
                txt_fadela_name_preview.text = "الغني اللي كان وكان"
            }
            14 -> {
                audio_ID = R.raw.asfora_soghera
                txt_fadela_name_preview.text = "العصفورة الصغيرة"
            }
            15 -> {
                audio_ID = R.raw.bent_soghera
                txt_fadela_name_preview.text = "البنت الصغيرة"
            }
            16 -> {
                audio_ID = R.raw.kalb_sogher
                txt_fadela_name_preview.text = "الكلب الصغير"
            }
            17 -> {
                audio_ID = R.raw.walad_kaslan
                txt_fadela_name_preview.text = "الولد الكسلان"
            }
            18 -> {
                audio_ID = R.raw.kalb_meshmshy
                txt_fadela_name_preview.text = "الكلب المشمشي"
            }
            19 -> {
                audio_ID = R.raw.hassan
                txt_fadela_name_preview.text = "حسن"
            }
            20 -> {
                audio_ID = R.raw.katkot
                txt_fadela_name_preview.text = "الكتكوت"
            }
            21 -> {
                audio_ID = R.raw.me3da
                txt_fadela_name_preview.text = "المعدة"
            }
            22 -> {
                audio_ID = R.raw.ragel_ghany_ragel_faer
                txt_fadela_name_preview.text = "الرجل الغني والرجل الفقير"
            }
            23 -> {
                audio_ID = R.raw.sayad
                txt_fadela_name_preview.text = "الصياد"
            }
            24 -> {
                audio_ID = R.raw.set_tayeba
                txt_fadela_name_preview.text = "الست الطيبة"
            }
            25 -> {
                audio_ID = R.raw.faeer
                txt_fadela_name_preview.text = "الفقير"
            }
        }


        var position = 0
        mediaPlayer = MediaPlayer.create(this, audio_ID)

        //set audio duration
        finalTime = mediaPlayer?.duration!!.toDouble()
        txt_fadela_duration.text = String.format(
            "%d:%d",
            TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()))
        )


        btn_play_f.setOnClickListener {
            mediaPlayer?.seekTo(position)
            mediaPlayer?.start()
            btn_play_f.visibility = View.GONE
            btn_pause_f.visibility = View.VISIBLE

            if (oneTimeOnly == 0) {
                fadela_seekbar!!.max = finalTime.toInt()
                // oneTimeOnly = 1
            }

            fadela_seekbar!!.progress = startTime.toInt()
            handler.postDelayed(updateSongTime, 100)
        }

        btn_pause_f.setOnClickListener {
            position = mediaPlayer?.currentPosition!!
            mediaPlayer?.pause()
            btn_pause_f.visibility = View.GONE
            btn_play_f.visibility = View.VISIBLE
        }

        btn_stop_f.setOnClickListener {
            mediaPlayer?.stop()
            position = 0
            btn_pause_f.visibility = View.GONE
            btn_play_f.visibility = View.VISIBLE
            mediaPlayer = MediaPlayer.create(this, audio_ID)
        }

        fadela_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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
            btn_pause_f.visibility = View.GONE
            btn_play_f.visibility = View.VISIBLE
            mediaPlayer = MediaPlayer.create(this, audio_ID)
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