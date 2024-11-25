package com.example.assignment.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assignment.data.model.Post
import com.example.assignment.data.remote.api.ApiService

class PostPagingSource(private val apiService: ApiService) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val currentPage = params.key ?: 0
            val response = apiService.getPosts(limit = 10, skip = currentPage * 10)
            LoadResult.Page(
                data = response.posts,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (response.posts.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }
}