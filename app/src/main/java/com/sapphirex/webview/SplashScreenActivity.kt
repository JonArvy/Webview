package com.sapphirex.webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {
    private val delay: Long = 3000 //3 sec splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        val img_splash = findViewById<ImageView>(R.id.img_splash)
//        img_splash.animate().setDuration(1000).alpha(1f).withEndAction() {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

        val rootView: View = findViewById(android.R.id.content)
        rootView.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delay)


    }
}