package com.example.assignment.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.assignment.data.local.db.AppDatabase
import com.example.assignment.data.remote.api.ApiService
import com.example.assignment.di.LocalInjector

class FetchUsersWork(
    private val context: Context, workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val database = LocalInjector.injectDb()
        val userDao = database?.postDao()
        val apiService = ApiService.RetrofitInstance.api

        return try {
            // Determine how many users are already cached
//            val cachedUsersCount = userDao.getAllUsers().size
//            val limit = 10
//            val skip = cachedUsersCount
//
//            // Fetch users from the API
//            val users = apiService.fetchUsers(limit = limit, skip = skip)
//
//            // Cache the fetched users in the database
//            userDao.insertUsers(users)

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}