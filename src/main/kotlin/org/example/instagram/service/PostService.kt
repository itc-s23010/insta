package org.example.instagram.service

import jakarta.transaction.Transactional
import org.example.instagram.model.Post
import org.example.instagram.repository.CommentRepository
import org.example.instagram.repository.PostRepository
import org.springframework.stereotype.Service

@Service
@Transactional
class PostService(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository
) {

    fun getAllPosts(): List<Post> = postRepository.findAll()

    fun createPost(post: Post): Post {
        val newPost = Post(
            username = post.username,
            content = post.content,
            imageUrl = post.imageUrl
        )
        return postRepository.save(newPost)
    }
    fun updatePost(id: Long, updatedPost: Post): Post? {
        return postRepository.findById(id).map {
            val postToUpdate = it.copy(username = updatedPost.username, content = updatedPost.content, imageUrl = updatedPost.imageUrl)
            postRepository.save(postToUpdate)
        }.orElse(null)
    }

    fun deletePost(id: Long) {
        val post = postRepository.findById(id).orElse(null)
        if (post != null) {
            // 関連するコメントを削除
            commentRepository.deleteAllByPost(post)
            postRepository.delete(post)
        }
    }
    fun getPostById(id: Long): Post? {
        return postRepository.findById(id).orElse(null)
    }

}