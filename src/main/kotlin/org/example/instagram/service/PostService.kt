package org.example.instagram.service

import org.example.instagram.model.Post
import org.example.instagram.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {

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
        postRepository.deleteById(id)
    }
}