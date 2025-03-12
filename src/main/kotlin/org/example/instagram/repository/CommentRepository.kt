package org.example.instagram.repository

import org.example.instagram.model.Comment
import org.example.instagram.model.Post
import org.springframework.data.jpa.repository.JpaRepository


interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByPostId(postId: Long): List<Comment> // 投稿に紐づくコメントを取得
    fun deleteAllByPost(post: Post)
}