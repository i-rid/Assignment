package com.example.assignment.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment.data.local.entity.PostEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)


//    @Query("SELECT * FROM posts ORDER BY id ASC LIMIT :limit OFFSET :offset")
//    @Query("SELECT * FROM posts ORDER BY id ASC")
    @Query("SELECT * FROM posts")
    fun getPaginatedPosts(): PagingSource<Int, PostEntity>

    @Query("DELETE FROM posts")
    suspend fun clearPosts()
}
