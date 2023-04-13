package com.azathoth.handlistserver.model.task

import com.azathoth.handlistserver.model.spacenode.SpaceNodeRepository
import com.azathoth.handlistserver.model.user.UserRepository
import com.azathoth.handlistserver.model.user.UserRequest
import com.azathoth.handlistserver.model.user.UserResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TaskService(
    private val taskRepo: TaskRepository,
    private val nodeRepo: SpaceNodeRepository,
    private val userRepo: UserRepository,
) {
    fun getAll(): MutableIterable<Task> = taskRepo.findAll()

    fun findByName(name: String) = taskRepo.findByName(name)

    fun findById(taskId: Long): Task =
        taskRepo.findByIdOrNull(taskId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun insert(task: Task): Task = taskRepo.save(task)

    fun deleteById(taskId: Long): Unit =
        if (taskRepo.existsById(taskId)) taskRepo.deleteById(taskId)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun update(taskId: Long, task: Task): Task {
        val oldTask = taskRepo.findByIdOrNull(taskId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return oldTask.apply {
            name = task.name
            status = task.status
            description = task.description
            createTime = task.createTime
            startTime = task.startTime
            endTime = task.endTime

            taskRepo.save(oldTask)
        }
    }

    fun getAllTasksBySpaceNodeId(nodeId: Long) =
        if (nodeRepo.existsById(nodeId)) {
            taskRepo.findBySpaceNodeId(nodeId)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun insertTask(nodeId: Long, task: Task) =
        nodeRepo.findByIdOrNull(nodeId)?.let {
            task.spaceNode = it
            insert(task)
        }

    fun deleteAllTasksBySpaceNodeId(nodeId: Long) =
        if (nodeRepo.existsById(nodeId)) {
            taskRepo.deleteBySpaceNodeId(nodeId)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getAllUsers(taskId: Long): List<UserResponse> {
        val task = taskRepo.findByIdOrNull(taskId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return task.assigns.map { UserResponse(email = it.email, nickname = it.nickname, role = it.role) }
    }

    fun assignUser(taskId: Long, userRequest: UserRequest): Task? {
        val task = taskRepo.findByIdOrNull(taskId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val user = userRepo.findByEmail(userRequest.email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val ifAssigned = task.assign(user)
        return if (ifAssigned) taskRepo.save(task) else null
    }

    fun freeUser(taskId: Long, userRequest: UserRequest): Task? {
        val task = taskRepo.findByIdOrNull(taskId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val user = userRepo.findByEmail(userRequest.email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val ifFreed = task.free(user)

        return if (ifFreed) taskRepo.save(task) else null
    }

    fun getAllTasksByUserEmail(email: String) = taskRepo.findByAssignsEmail(email)
}