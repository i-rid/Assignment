package com.example.assignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.assignment.data.remote.api.ApiService

class PostRepository(private val apiService: ApiService) {
    fun getPostsLiveData() = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PostPagingSource(apiService) }
    ).liveData
}