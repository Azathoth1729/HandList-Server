package com.azathoth.handlistserver.model.task

import com.azathoth.handlistserver.model.spacenode.SpaceNode
import com.azathoth.handlistserver.model.user.User
import com.azathoth.handlistserver.model.user.UserDTO
import com.azathoth.handlistserver.model.user.UserRepository
import com.azathoth.handlistserver.model.user.toDTO
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

class TaskDTO(
    var id: Long = 0,
    var name: String,
    var status: Status,
    var description: String,
    val createTime: LocalDate,
    var startTime: LocalDate?,
    var endTime: LocalDate?,
    @JsonIgnoreProperties(value = ["hibernateLazyInitializer", "handler"])
    var spaceNode: SpaceNode?,
    var assigns: List<UserDTO>,
)

fun Task.toDTO(): TaskDTO = TaskDTO(
    id = this.id,
    name = this.name,
    status = this.status,
    description = this.description,
    createTime = this.createTime,
    startTime = this.startTime,
    endTime = this.endTime,
    spaceNode = this.spaceNode,
    assigns = this.assigns.map(User::toDTO)
)

fun TaskDTO.toTask(userRepo: UserRepository) = Task(
    id = this.id,
    name = this.name,
    status = this.status,
    description = this.description,
    createTime = this.createTime,
    startTime = this.startTime,
    endTime = this.endTime,
    spaceNode = this.spaceNode,
    assigns = this.assigns.map {
        it.email?.let { it1 -> userRepo.findByEmail(it1) } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }.toMutableSet()
)