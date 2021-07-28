package com.gkreduction.interview.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.gkreduction.interview.R
import com.gkreduction.interview.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        timer()
    }

    private fun timer() {
        val timer = object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                navigationToMain()
            }

            override fun onTick(p0: Long) {
            }
        }
        timer.start()
    }

    fun navigationToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}