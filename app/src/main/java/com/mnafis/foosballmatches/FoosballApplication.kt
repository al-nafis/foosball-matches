package com.mnafis.foosballmatches

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.mnafis.foosballmatches.di.AppComponent
import com.mnafis.foosballmatches.di.DaggerAppComponent

class FoosballApplication : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}