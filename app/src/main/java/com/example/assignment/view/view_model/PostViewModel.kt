package com.example.assignment.view.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.assignment.data.model.Post
import com.example.assignment.data.remote.api.ApiService
import com.example.assignment.data.repository.PostRepository
import com.example.assignment.data.repository.PostRepository2
import com.example.assignment.data.local.entity.PostEntity
import com.example.assignment.utils.AppUiState

class ContentViewModel: ViewModel() {

    private var _posts = MutableLiveData<AppUiState>(AppUiState.Loading)
    val posts: LiveData<AppUiState> = _posts

    private val repository = PostRepository(ApiService.RetrofitInstance.api)
    private val repositoryLocal = PostRepository2(
        api = ApiService.RetrofitInstance.api
    )

    fun fetchPostsLiveData(): LiveData<PagingData<Post>> {
        return repository.getPostsLiveData()
            .map { pagingData -> pagingData.map { it } } // Transform data if needed
            .cachedIn(viewModelScope)
    }

    // LiveData to observe the paginated data
    fun fetchPosts(): LiveData<PagingData<PostEntity>> {
        return repositoryLocal.fetchPosts().cachedIn(viewModelScope) // Cache in ViewModelScope
    }
}