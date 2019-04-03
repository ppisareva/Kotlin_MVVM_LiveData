package com.example.android.guesstheword

import android.app.Application
import timber.log.Timber

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        // register a log library
        Timber.plant(Timber.DebugTree())
    }


}