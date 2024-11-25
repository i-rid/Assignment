package com.example.assignment

import android.app.Application
import com.example.assignment.di.LocalInjector
import com.example.assignment.data.local.db.AppDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        LocalInjector.appDatabase = AppDatabase.getInstance(this@MyApplication)
    }
}