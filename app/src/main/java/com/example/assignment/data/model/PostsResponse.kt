package com.example.assignment.data.model

data class PostResponse(
    val posts: List<Post>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String>,
    val reactions: Reactions,
    val views: Int,
    val userId: Int
)

data class Reactions(
    val likes: Int,
    val dislikes: Int
)

