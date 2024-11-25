package com.example.assignment.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.assignment.data.local.db.AppDatabase
import com.example.assignment.data.local.entity.PostEntity
import com.example.assignment.data.model.Post
import com.example.assignment.data.remote.api.ApiService
import com.example.assignment.data.local.entity.RemoteKeys

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(
    private val database: AppDatabase,
    private val api: ApiService
) : RemoteMediator<Int, PostEntity>() {

    private val postDao = database.postDao()
    private val remoteKeysDao = database.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                null // Start fresh
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = api.getPosts(limit = state.config.pageSize, skip = page ?: 0)

            val posts = apiResponse.posts
            val endOfPaginationReached = posts.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    postDao.clearPosts()
                    remoteKeysDao.clearRemoteKeys()
                }

                val keys = posts.map {
                    RemoteKeys(
                        postId = it.id,
                        prevKey = if (page == 0) null else page?.minus(state.config.pageSize),
                        nextKey = if (endOfPaginationReached) null else page?.plus(state.config.pageSize)
                    )
                }

                remoteKeysDao.insertAll(keys)
                /*
                getting error
                Type mismatch.
                Required: PostEntity
                Found: Unit
                Unresolved reference: toEntity
                * */
                postDao.insertPosts(posts.map { it.toEntity() })
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PostEntity>): RemoteKeys? {
        return state.lastItemOrNull()?.let { post ->
            remoteKeysDao.remoteKeysByPostId(post.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PostEntity>): RemoteKeys? {
        return state.firstItemOrNull()?.let { post ->
            remoteKeysDao.remoteKeysByPostId(post.id)
        }
    }
}
fun Post.toEntity(): PostEntity {
    return PostEntity(
        id = this.id,
        title = this.title,
        body = this.body,
//        tags = this.tags,
        likes = this.reactions.likes,
        dislikes = this.reactions.dislikes,
        views = this.views
    )
}
