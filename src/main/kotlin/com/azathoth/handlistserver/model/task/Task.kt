package com.azathoth.handlistserver.model.task

import com.azathoth.handlistserver.model.spacenode.SpaceNode
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
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
    var createTime: LocalDate = LocalDate.now(),

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    var startTime: LocalDate? = null,

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    var endTime: LocalDate? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_node_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    var spaceNode: SpaceNode?,
)
