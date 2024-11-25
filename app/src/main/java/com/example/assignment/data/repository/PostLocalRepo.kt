package com.example.assignment.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.assignment.data.remote.api.ApiService
import com.example.assignment.data.local.db.AppDatabase
import com.example.assignment.data.local.entity.PostEntity
import com.example.assignment.di.LocalInjector

class PostRepository2(
    private val database: AppDatabase? = LocalInjector.injectDb(),
    private val api: ApiService
) {
    private val pagingSourceFactory = { database!!.postDao().getPaginatedPosts() }

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): LiveData<PagingData<PostEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = PostRemoteMediator(database!!, api),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }
}
