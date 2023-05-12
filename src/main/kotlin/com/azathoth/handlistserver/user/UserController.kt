package com.azathoth.handlistserver.user

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.user.model.UserDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/$API_VERSION/users")
class UserController(private val service: UserService) {
    @GetMapping
    fun getAll() = service.getAll()

    // get all tasks assigned to a user
    @GetMapping("{user_email}/tasks")
    fun getAllTasksByUserEmail(@PathVariable("user_email") email: String) =
        service.getAllTasksByUserEmail(email)

    @PutMapping("{user_id}")
    fun update(@PathVariable("user_id") userId: Long, @RequestBody user: UserDTO) =
        service.update(userId, user)

    @DeleteMapping("{user_id}")
    fun deleteById(@PathVariable("user_id") userId: Long) =
        service.deleteById(userId)

}