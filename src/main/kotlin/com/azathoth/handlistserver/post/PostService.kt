package com.azathoth.handlistserver.post

import com.azathoth.handlistserver.post.reply.ReplyDTO
import com.azathoth.handlistserver.post.reply.ReplyRepo
import com.azathoth.handlistserver.post.reply.toReply
import com.azathoth.handlistserver.post.tag.PostTagRepo
import com.azathoth.handlistserver.user.UserRepo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@Service
class PostService(
    private val postRepo: PostRepo,
    private val userRepo: UserRepo,
    private val replyRepo: ReplyRepo,
    private val postTagRepo: PostTagRepo
) {
    fun findAll() = postRepo.findAll().map { it.toDTO() }

    fun findById(id: Long) = postRepo.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun insert(email: String, postDTO: PostDTO) =
        postRepo.save(
            Post(
                title = postDTO.title ?: "",
                content = postDTO.content ?: "",
                createTime = postDTO.createTime ?: LocalDate.now(),
                lastModifiedTime = postDTO.lastModifiedTime ?: LocalDate.now(),
                user = userRepo.findByEmail(email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND),
                replies = HashSet(),
                tags = postDTO.tags?.map { replyDTO ->
                    replyDTO.id?.let {
                        postTagRepo.findByIdOrNull(it)
                    } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
                }?.toMutableSet() ?: HashSet(),
            )
        ).toDTO()


    fun update(postId: Long, postDTO: PostDTO): PostDTO {
        val oldPost = postRepo.findByIdOrNull(postId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return oldPost.apply {
            postDTO.title?.let { title = it }
            postDTO.content?.let { content = it }
            postDTO.lastModifiedTime?.let { lastModifiedTime = it }
            postDTO.user?.let {
                user = userRepo.findByEmail(it.email ?: throw ResponseStatusException(HttpStatus.NOT_FOUND))
                    ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
            }
            postDTO.replies?.map { replyDTO ->
                replyDTO.id?.let {
                    replyRepo.findByIdOrNull(it)
                } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
            }?.toMutableSet()?.let { replies = it }

            postDTO.tags?.map { replyDTO ->
                replyDTO.id?.let {
                    postTagRepo.findByIdOrNull(it)
                } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
            }?.toMutableSet()?.let { tags = it }
        }.toDTO()
    }


    fun deleteById(postId: Long) = postRepo.deleteById(postId)

    fun addReply(postId: Long, replyDTO: ReplyDTO): Boolean {
        val post = findById(postId)
        val reply = replyDTO.toReply(userRepo, postRepo)
        return post.addReply(reply)
    }

    fun rmReply(postId: Long, replyDTO: ReplyDTO): Boolean {
        val post = findById(postId)
        val reply = replyDTO.toReply(userRepo, postRepo)
        return post.rmReply(reply)
    }

}