package org.galib.feedmenews

import android.app.Application
import android.content.Context
import org.galib.feedmenews.di.initKoin

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}