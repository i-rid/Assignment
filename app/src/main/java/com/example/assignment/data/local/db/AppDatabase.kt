package com.example.assignment.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment.data.local.dao.PostDao
import com.example.assignment.data.local.dao.RemoteKeysDao
import com.example.assignment.data.local.entity.PostEntity
import com.example.assignment.data.local.entity.RemoteKeys


@Database(entities = [PostEntity::class, RemoteKeys::class], version = 1)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        val POST_DB = "posts.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, POST_DB)
                .build()
    }

}

