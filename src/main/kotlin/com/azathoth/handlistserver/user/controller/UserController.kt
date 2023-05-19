package com.azathoth.handlistserver.user.controller

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.user.UserService
import com.azathoth.handlistserver.user.model.UserDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/$API_VERSION/users")
class UserController(private val service: UserService) {
    @GetMapping
    fun getAllUsers() = service.getAll()

    // get all tasks assigned to a user
    @GetMapping("{user_email}/tasks")
    fun getTasksByUserEmail(@PathVariable("user_email") email: String) =
        service.getTasksByUserEmail(email)

    @GetMapping("{user_email}")
    fun findUserByEmail(@PathVariable("user_email") email: String) =
        service.findUserByEmail(email)

    @PutMapping("{user_id}")
    fun update(@PathVariable("user_id") userId: Long, @RequestBody user: UserDTO) =
        service.update(userId, user)

    @DeleteMapping("{user_id}")
    fun deleteById(@PathVariable("user_id") userId: Long) =
        service.deleteById(userId)

}