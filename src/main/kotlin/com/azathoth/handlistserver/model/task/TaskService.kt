package com.azathoth.handlistserver.model.task

import com.azathoth.handlistserver.model.spacenode.SpaceNodeRepository
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

    fun update(id: Long, task: Task): Task {
        val oldTask = taskRepo.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        oldTask.name = task.name
        oldTask.status = task.status
        oldTask.description = task.description
        oldTask.createTime = task.createTime
        oldTask.startTime = task.startTime
        oldTask.endTime = task.endTime
        taskRepo.save(oldTask)
        return oldTask
    }


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