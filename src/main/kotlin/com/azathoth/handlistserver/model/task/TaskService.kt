package com.azathoth.handlistserver.model.task

import com.azathoth.handlistserver.repository.SpaceNodeRepository
import com.azathoth.handlistserver.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TaskService(@Autowired val taskRepo: TaskRepository, @Autowired val nodeRepo: SpaceNodeRepository) {

    fun getAll(): MutableIterable<Task> = taskRepo.findAll()

    fun findByName(name: String) = taskRepo.findByName(name)

    fun findById(id: Long): Task =
        taskRepo.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun insert(task: Task): Task = taskRepo.save(task)

    fun deleteById(id: Long): Unit =
        if (taskRepo.existsById(id)) taskRepo.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun update(id: Long, task: Task): Task =
        if (taskRepo.existsById(id)) {
            task.id = id
            taskRepo.save(task)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getAllTasksBySpaceNodeId(node_id: Long) =
        if (nodeRepo.existsById(node_id)) {
            taskRepo.findBySpaceNodeId(node_id)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)


    fun insertTask(node_id: Long, task: Task) =
        nodeRepo.findByIdOrNull(node_id).let {
            task.spaceNode = it
            insert(task)
        }

    fun deleteAllTasksBySpaceNodeId(node_id: Long) =
        if (nodeRepo.existsById(node_id)) {
            taskRepo.deleteBySpaceNodeId(node_id)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
}