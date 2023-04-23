package com.azathoth.handlistserver.task

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.task.model.TaskDTO
import com.azathoth.handlistserver.user.UserRequest
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

    @GetMapping("/tasks/{task_id}")
    fun getTask(@PathVariable("task_id") taskId: Long) = service.findById(taskId)

    @DeleteMapping("/tasks/{task_id}")
    fun deleteById(@PathVariable("task_id") taskId: Long) = service.deleteById(taskId)

    @PutMapping("/tasks/{task_id}")
    fun update(@PathVariable("task_id") taskId: Long, @RequestBody taskDTO: TaskDTO) =
        service.update(taskId, taskDTO)

    // get all users of a task
    @GetMapping("/tasks/{task_id}/users")
    fun getAllUsers(@PathVariable("task_id") taskId: Long) =
        service.getAllUsers(taskId)

    // assign a user to a task
    @PostMapping("/tasks/{task_id}/users")
    fun assignUser(@PathVariable("task_id") taskId: Long, @RequestBody userRequest: UserRequest) =
        service.assignUser(taskId, userRequest)

    // free a user from a task
    @DeleteMapping("/tasks/{task_id}/users")
    fun freeUser(@PathVariable("task_id") taskId: Long, @RequestBody userRequest: UserRequest) =
        service.freeUser(taskId, userRequest)

    // get all tasks assigned to a user
    @GetMapping("/users/{user_email}/tasks")
    fun getAllTasksByUserEmail(@PathVariable("user_email") email: String) =
        service.getAllTasksByUserEmail(email)

    // get all tasks from a spacenode
    @GetMapping("/spacenodes/{node_id}/tasks")
    fun getAllTasksBySpaceNodeId(@PathVariable("node_id") nodeId: Long) = service.getAllTasksBySpaceNodeId(nodeId)

    //insert a new task to a spacenode
    @PostMapping("/spacenodes/{node_id}/tasks")
    fun insertTask(@PathVariable("node_id") nodeId: Long, @RequestBody taskDTO: TaskDTO) =
        service.insertTask(nodeId, taskDTO)

    //delete all tasks of a spacenode
    @DeleteMapping("/spacenodes/{node_id}/tasks")
    fun deleteAllTask(@PathVariable("node_id") nodeId: Long) =
        service.deleteAllTasksBySpaceNodeId(nodeId)
}