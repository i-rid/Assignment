package com.example.assignment.di

import com.example.assignment.data.local.db.AppDatabase

object LocalInjector {

    var appDatabase: AppDatabase? = null

    fun injectDb(): AppDatabase? {
        return appDatabase
    }
}