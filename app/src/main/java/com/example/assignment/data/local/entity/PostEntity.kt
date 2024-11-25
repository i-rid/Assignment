package com.example.assignment.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
//    val tags: List<String>,
    val likes: Int,
    val dislikes: Int,
    val views: Int
)


//class Converters {
//
//    @TypeConverter
//    fun fromTagsList(tags: List<String>?): String {
//        return Gson().toJson(tags)
//    }
//
//    @TypeConverter
//    fun toTagsList(tagsString: String): List<String>? {
//        val listType = object : TypeToken<List<String>>() {}.type
//        return Gson().fromJson(tagsString, listType)
//    }
//}