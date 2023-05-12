package com.azathoth.handlistserver.post.reply

import com.azathoth.handlistserver.post.PostRepo
import com.azathoth.handlistserver.user.UserRepo
import com.azathoth.handlistserver.user.model.UserDTO
import com.azathoth.handlistserver.user.model.toDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

data class ReplyDTO(
    val id: Long?,
    val content: String?,
    val createTime: LocalDate?,
    val lastModifiedTime: LocalDate?,
    val user: UserDTO?,
    val postId: Long?,
)

fun Reply.toDTO(): ReplyDTO = ReplyDTO(
    id = id,
    content = content,
    createTime = createTime,
    lastModifiedTime = lastModifiedTime,
    user = user.toDTO(),
    postId = post.id
)

fun ReplyDTO.toReply(userRepo: UserRepo, postRepo: PostRepo): Reply = Reply(
    content = this.content ?: "",
    createTime = this.createTime!!,
    lastModifiedTime = this.lastModifiedTime!!,
    user = this.user?.email?.let { userRepo.findByEmail(it) }
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND),
    post = this.postId?.let { postRepo.findByIdOrNull(it) }
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND),
)