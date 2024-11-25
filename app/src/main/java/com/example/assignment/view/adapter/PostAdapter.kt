package com.example.assignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.data.model.Post
import com.example.assignment.R

class PostAdapter : PagingDataAdapter<Post, PostAdapter.PostViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        post?.let {
            holder.bind(it)
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.postTitle)
        private val body: TextView = itemView.findViewById(R.id.postBody)
        private val tags: TextView = itemView.findViewById(R.id.postTags)
        private val likes: TextView = itemView.findViewById(R.id.postLikes)
        private val dislikes: TextView = itemView.findViewById(R.id.postDislikes)
        private val views: TextView = itemView.findViewById(R.id.postViews)

        fun bind(post: Post) {
            title.text = post.title
            body.text = post.body
            tags.text = "Tags: ${post.tags.joinToString(", ")}"
            likes.text = "Likes: ${post.reactions.likes}"
            dislikes.text = "Dislikes: ${post.reactions.dislikes}"
            views.text = "Views: ${post.views}"
        }
    }

    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
        }
    }
}
