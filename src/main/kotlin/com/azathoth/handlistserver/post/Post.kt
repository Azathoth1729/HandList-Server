package com.azathoth.handlistserver.post

import com.azathoth.handlistserver.post.reply.Reply
import com.azathoth.handlistserver.post.tag.PostTag
import com.azathoth.handlistserver.user.model.User
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate

@Entity(name = "post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    var title: String,
    var content: String,

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    var createTime: LocalDate = LocalDate.now(),
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    var lastModifiedTime: LocalDate = createTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = ["hibernateLazyInitializer", "handler"])
    var user: User,


    @OneToMany(
        mappedBy = "post", fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var replies: MutableSet<Reply> = HashSet(),

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE
        ]
    )
    @JoinTable(
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    var tags: MutableSet<PostTag> = HashSet()
) {

    fun addReply(reply: Reply) = this.replies.add(reply)

    fun rmReply(reply: Reply) = this.replies.remove(reply)

    fun addTag(tag: PostTag) = this.tags.add(tag)

    fun rmTag(tag: PostTag) = this.tags.remove(tag)
}