package com.azathoth.handlistserver.post.reply

import org.springframework.data.repository.CrudRepository

interface ReplyRepo : CrudRepository<Reply, Long> {
}