package org.example.instagram.service

import org.example.instagram.model.Comment
import org.example.instagram.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(private val commentRepository: CommentRepository) {

    fun getCommentsByPostId(postId: Long): List<Comment> {
        return commentRepository.findByPostId(postId)
    }

    fun addComment(comment: Comment): Comment {
        return commentRepository.save(comment)
    }
}