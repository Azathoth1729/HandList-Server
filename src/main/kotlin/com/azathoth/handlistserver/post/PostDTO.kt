package com.azathoth.handlistserver.post

import com.azathoth.handlistserver.post.reply.ReplyDTO
import com.azathoth.handlistserver.post.reply.toDTO
import com.azathoth.handlistserver.post.tag.PostTagDTO
import com.azathoth.handlistserver.post.tag.toDTO
import com.azathoth.handlistserver.user.model.UserDTO
import com.azathoth.handlistserver.user.model.toDTO
import java.time.LocalDate

data class PostDTO(
    val id: Long?,
    val title: String?,
    val content: String?,
    var createTime: LocalDate?,
    var lastModifiedTime: LocalDate?,
    var user: UserDTO?,
    var replies: List<ReplyDTO>?,
    var tags: List<PostTagDTO>?
)

fun Post.toDTO(): PostDTO = PostDTO(
    id = id,
    title = title,
    content = content,
    createTime = createTime,
    lastModifiedTime = lastModifiedTime,
    user = user.toDTO(),
    replies = replies.map { it.toDTO() },
    tags = tags.map { it.toDTO() }
)