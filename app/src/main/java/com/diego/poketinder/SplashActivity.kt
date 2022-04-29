package com.diego.poketinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.AlarmClock
import androidx.appcompat.app.AlertDialog
import com.diego.poketinder.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = Intent(this,MainActivity::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE,"message") }
        //SE PROGRAMA EL TIEMPO DE DURACIÓN DEL SPLASH ANTES DE PASAR AL MAIN ACTIVITY
        Handler(Looper.getMainLooper()).postDelayed({startActivity(intent); finish()},3000)
        }

}

