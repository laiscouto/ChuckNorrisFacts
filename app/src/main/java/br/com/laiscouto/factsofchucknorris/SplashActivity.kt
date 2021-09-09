package br.com.laiscouto.factsofchucknorris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val x = findViewById<TextView>(R.id.hello)
        val y = findViewById<ProgressBar>(R.id.progress)
        x.alpha = 0f
        y.alpha = 0f
        x.animate().setDuration(1500).alpha(1f).withEndAction {
            y.animate().setDuration(2000).alpha(1f)
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}