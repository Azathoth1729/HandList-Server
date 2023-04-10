package com.azathoth.handlistserver.controller

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.model.task.Task
import com.azathoth.handlistserver.model.task.TaskService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/$API_VERSION")
class TaskController(val service: TaskService) {
    @GetMapping("/tasks")
    fun getAll(@RequestParam name: String?) =
        if (name == null) {
            service.getAll()
        } else {
            service.findByName(name)
        }

    @GetMapping("/tasks/{id}")
    fun getTask(@PathVariable id: Long) = service.findById(id)

    @DeleteMapping("/tasks/{id}")
    fun deleteById(@PathVariable id: Long) = service.deleteById(id)

    @PutMapping("/tasks/{id}")
    fun update(@PathVariable id: Long, @RequestBody task: Task) = service.update(id, task)

    // get all tasks from a spacenode
    @GetMapping("/spacenodes/{node_id}/tasks")
    fun getAllTask(@PathVariable node_id: Long) = service.getAllTasksBySpaceNodeId(node_id)

    //insert a new task to a spacenode

    @PostMapping("/spacenodes/{node_id}/tasks")
    fun insertTask(@PathVariable node_id: Long, @RequestBody task: Task) =
        service.insertTask(node_id, task)

    //delete all tasks of a spacenode
    @DeleteMapping("/spacenodes/{node_id}/tasks")
    fun deleteAllTask(@PathVariable node_id: Long) =
        service.deleteAllTasksBySpaceNodeId(node_id)
}