package org.example.instagram.controller

import org.example.instagram.model.Post
import org.example.instagram.service.PostService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ViewController(private val postService: PostService) {

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
}