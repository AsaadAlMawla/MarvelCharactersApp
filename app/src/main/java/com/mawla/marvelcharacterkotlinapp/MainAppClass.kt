package com.mawla.marvelcharacterkotlinapp

import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco

class MainAppClass : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}