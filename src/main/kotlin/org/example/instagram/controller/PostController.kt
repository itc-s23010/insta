package org.example.instagram.controller

import org.example.instagram.model.Post
import org.example.instagram.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostController(private val postService: PostService) {

    @GetMapping
    fun getAllPosts(): List<Post> = postService.getAllPosts()

    @PostMapping
    fun createPost(@RequestBody post: Post): Post = postService.createPost(post)

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody post: Post): ResponseEntity<Post> {
        return postService.updatePost(id, post)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<Void> {
        postService.deletePost(id)
        return ResponseEntity.noContent().build()
    }
}