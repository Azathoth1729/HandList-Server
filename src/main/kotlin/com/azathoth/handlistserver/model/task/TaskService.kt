package com.azathoth.handlistserver.model.task

import com.azathoth.handlistserver.model.spacenode.SpaceNodeRepository
import com.azathoth.handlistserver.model.user.*
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
    fun getAll() = taskRepo.findAll().map(Task::toDTO)

    fun findByName(name: String) = taskRepo.findByName(name).map(Task::toDTO)

    fun findById(taskId: Long) =
        taskRepo.findByIdOrNull(taskId)?.toDTO() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun insert(task: Task) = taskRepo.save(task).toDTO()

    fun deleteById(taskId: Long) =
        if (taskRepo.existsById(taskId)) taskRepo.deleteById(taskId)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun update(taskId: Long, taskDTO: TaskDTO): TaskDTO {
        val oldTask = taskRepo.findByIdOrNull(taskId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return oldTask
            .apply {
                taskDTO.name.let { name = it }
                taskDTO.status.let { status = it }
                taskDTO.description.let { description = it }
                taskDTO.startTime?.let { startTime = it }
                taskDTO.endTime?.let { endTime = it }

                taskDTO.spaceNode?.let {
                    spaceNode = it
                }

                taskDTO.assigns.map {
                    it.email?.let { it1 -> userRepo.findByEmail(it1) }
                        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
                }.toMutableSet().let {
                    assigns = it
                }

                taskRepo.save(oldTask)
            }
            .run { this.toDTO() }
    }

    fun getAllTasksBySpaceNodeId(nodeId: Long) =
        if (nodeRepo.existsById(nodeId)) {
            taskRepo.findBySpaceNodeId(nodeId).map(Task::toDTO)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun insertTask(nodeId: Long, taskDTO: TaskDTO) =
        nodeRepo.findByIdOrNull(nodeId)?.let {
            taskDTO.spaceNode = it
            insert(taskDTO.toTask(userRepo))
        }

    fun deleteAllTasksBySpaceNodeId(nodeId: Long) =
        if (nodeRepo.existsById(nodeId)) {
            taskRepo.deleteBySpaceNodeId(nodeId)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getAllUsers(taskId: Long): List<UserDTO> {
        val task = taskRepo.findByIdOrNull(taskId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return task.assigns.map { it.toDTO() }
    }

    fun assignUser(taskId: Long, userRequest: UserRequest): TaskDTO? {
        val task = taskRepo.findByIdOrNull(taskId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val user = userRepo.findByEmail(userRequest.email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val ifAssigned = task.assign(user)
        return if (ifAssigned) taskRepo.save(task).toDTO() else null
    }

    fun freeUser(taskId: Long, userRequest: UserRequest): TaskDTO? {
        val task = taskRepo.findByIdOrNull(taskId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val user = userRepo.findByEmail(userRequest.email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val ifFreed = task.free(user)

        return if (ifFreed) taskRepo.save(task).toDTO() else null
    }

    fun getAllTasksByUserEmail(email: String) = taskRepo.findByAssignsEmail(email).map(Task::toDTO)
}