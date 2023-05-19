package com.azathoth.handlistserver.post.reply

import com.azathoth.handlistserver.post.Post
import com.azathoth.handlistserver.user.model.User
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate

@Entity(name = "reply")
data class Reply(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    var content: String,

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    var createTime: LocalDate = LocalDate.now(),
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    var lastModifiedTime: LocalDate = createTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = ["hibernateLazyInitializer", "handler"])
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = ["hibernateLazyInitializer", "handler"])
    val post: Post
)