package com.mawla.marvelcharacterkotlinapp

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import com.mawla.marvelcharacterkotlinapp.databinding.SplashScreenMainLayoutBinding


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val binding = SplashScreenMainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val languageGif: SimpleDraweeView = binding.splashScreenImageView

        languageGif.controller = Fresco.newDraweeControllerBuilder()
            .setImageRequest(ImageRequest.fromUri("asset:///marvel.gif"))
            .setAutoPlayAnimations(true)
            .build()

        Handler().postDelayed(Runnable {
            launchMarvelCharactersActivity()
        }, 2500)

    }

    private fun launchMarvelCharactersActivity() {
        val marvelCharactersIntent = Intent(this, MarvelCharactersActivity::class.java)
        this.startActivity(marvelCharactersIntent)
        finish()
    }
}