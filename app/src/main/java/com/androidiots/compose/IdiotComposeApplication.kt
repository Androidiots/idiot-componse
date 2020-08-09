package com.androidiots.compose

import android.app.Application
import android.content.Context

class IdiotComposeApplication : Application() {

    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this@IdiotComposeApplication
    }
}