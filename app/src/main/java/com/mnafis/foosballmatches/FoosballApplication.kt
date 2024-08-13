package com.mnafis.foosballmatches

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class FoosballApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}