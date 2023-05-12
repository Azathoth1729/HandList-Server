package com.azathoth.handlistserver.post.controller

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.post.PostDTO
import com.azathoth.handlistserver.post.PostService
import com.azathoth.handlistserver.post.reply.ReplyDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/$API_VERSION")
class PostController(private val service: PostService) {
    @GetMapping("/posts")
    fun getAll() = service.findAll()

    @PostMapping("/users/{user_email}/posts")
    fun insert(@PathVariable("user_email") email: String, @RequestBody postDTO: PostDTO) =
        service.insert(email, postDTO)

    @PutMapping("/posts/{post_id}")
    fun update(@PathVariable("post_id") postId: Long, @RequestBody postDTO: PostDTO) =
        service.update(postId, postDTO)

    @DeleteMapping("/posts/{post_id}")
    fun deleteById(@PathVariable("post_id") postId: Long) = service.deleteById(postId)


    // assign a reply to a post
    @PostMapping("/posts/{post_id}/users")
    fun assignUser(@PathVariable("post_id") postId: Long, @RequestBody replyDTO: ReplyDTO) =
        service.addReply(postId, replyDTO)

    // remove a reply from a post
    @DeleteMapping("/posts/{post_id}/users")
    fun freeUser(@PathVariable("post_id") postId: Long, @RequestBody replyDTO: ReplyDTO) =
        service.rmReply(postId, replyDTO)

}