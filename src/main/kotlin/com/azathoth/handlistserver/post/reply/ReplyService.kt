package com.azathoth.handlistserver.post.reply

import com.azathoth.handlistserver.post.PostRepo
import com.azathoth.handlistserver.user.UserRepo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ReplyService(private val replyRepo: ReplyRepo, private val userRepo: UserRepo, private val postRepo: PostRepo) {
    fun findById(id: Long) = replyRepo.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun insert(replyDTO: ReplyDTO) = replyRepo.save(
        replyDTO.toReply(userRepo, postRepo)
    )

    fun delete(replyId: Long) = replyRepo.deleteById(replyId)
}