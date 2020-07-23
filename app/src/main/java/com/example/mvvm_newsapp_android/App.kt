package com.example.mvvm_newsapp_android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.ashokvarma.gander.Gander
import com.ashokvarma.gander.persistence.GanderPersistence
import com.facebook.drawee.backends.pipeline.Fresco

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        Fresco.initialize(this)
        Gander.setGanderStorage(GanderPersistence.getInstance(this))
    }

    companion object {

        /**
         * The application [Context] made static.
         * Do **NOT** use this as the context for a view,
         * this is mostly used to simplify calling of resources
         * (esp. String resources) outside activities.
         */
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }
}
