package com.example.assignment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val postId: Int, // Links each key to a Post
    val prevKey: Int?,           // The key for the previous page
    val nextKey: Int?            // The key for the next page
)
