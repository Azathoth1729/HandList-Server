package com.azathoth.handlistserver.model.task

import com.azathoth.handlistserver.model.spacenode.SpaceNode
import com.azathoth.handlistserver.model.user.User
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate


@Entity(name = "task")
class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "task_name")
    var name: String,

    @Enumerated(EnumType.STRING)
    var status: Status = Status.Todo,

    var description: String = "",

    @JsonProperty("create_time")
    var createTime: LocalDate = LocalDate.now(),

    @JsonProperty("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    var startTime: LocalDate? = null,

    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    var endTime: LocalDate? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_node_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    @JsonIgnoreProperties(value = ["hibernateLazyInitializer", "handler"])
    var spaceNode: SpaceNode,

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE
        ]
    )
    @JoinTable(
        name = "task_user",
        joinColumns = [JoinColumn(name = "task_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    var assigns: MutableSet<User> = hashSetOf(),
) {
    fun assign(user:User) = this.assigns.add(user)

    fun free(user:User) = this.assigns.remove(user)
}
