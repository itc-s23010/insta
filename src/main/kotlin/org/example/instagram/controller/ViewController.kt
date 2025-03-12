package org.example.instagram.controller

import org.example.instagram.model.Comment
import org.example.instagram.model.Post
import org.example.instagram.service.CommentService
import org.example.instagram.service.PostService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.*

@Controller
class ViewController(
    private val postService: PostService,
    private val commentService: CommentService
) {

    // 投稿詳細ページ（コメントも表示）
    @GetMapping("/posts/{id}")
    fun getPostDetails(@PathVariable id: Long, model: Model): String {
        val post = postService.getPostById(id) ?: return "redirect:/posts"
        val comments = commentService.getCommentsByPostId(id)
        model.addAttribute("post", post)
        model.addAttribute("comments", comments)
        model.addAttribute("newComment", Comment(post = post))
        return "post_details"
    }

    // コメントを追加
    @PostMapping("/posts/{id}/comments")
    fun addComment(@PathVariable id: Long, @ModelAttribute newComment: Comment): String {
        val post = postService.getPostById(id) ?: return "redirect:/posts"
        val commentToSave = Comment(
            post = post,
            username = newComment.username,
            content = newComment.content
        )
        commentService.addComment(commentToSave)
        return "redirect:/posts/$id"
    }

    @GetMapping("/posts")
    fun viewPosts(model: Model): String {
        model.addAttribute("posts", postService.getAllPosts())
        return "post_list"  // src/main/resources/templates/post_list.html を返す
    }

    @GetMapping("/create")
    fun showCreateForm(model: Model): String {
        model.addAttribute("post", Post())
        return "create_post"  // src/main/resources/templates/create_post.html を返す
    }

    @PostMapping("/posts/create")
    fun createPost(@ModelAttribute post: Post): String {
        postService.createPost(post)
        return "redirect:/posts"  // 投稿後、投稿一覧ページにリダイレクト
    }

    @GetMapping("/posts/delete/{id}")
    fun deletePost(@PathVariable id: Long): String {
        postService.deletePost(id)
        return "redirect:/posts"  // 投稿一覧にリダイレクト
    }

}