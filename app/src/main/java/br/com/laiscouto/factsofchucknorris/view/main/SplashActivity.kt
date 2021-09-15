package br.com.laiscouto.factsofchucknorris.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import br.com.laiscouto.factsofchucknorris.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashActivity()
    }

    fun splashActivity(){
        val img = findViewById<ImageView>(R.id.imgFacts)
        img.alpha = 0f
        img.animate().setDuration(1500).alpha(1f).withEndAction {
            Intent(this, HostActivity::class.java).apply {
                startActivity(this)
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}