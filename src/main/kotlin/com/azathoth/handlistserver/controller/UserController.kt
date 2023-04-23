package com.azathoth.handlistserver.controller

import com.azathoth.handlistserver.config.AppConfig.Companion.API_VERSION
import com.azathoth.handlistserver.model.user.UserDTO
import com.azathoth.handlistserver.model.user.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/$API_VERSION/users")
class UserController(private val service: UserService) {
    @GetMapping
    fun getAll() = service.getAll()

    @PutMapping("{user_id}")
    fun update(@PathVariable("user_id") userId: Long, @RequestBody user: UserDTO) =
        service.update(userId, user)

    @DeleteMapping("{user_id}")
    fun deleteById(@PathVariable("user_id") userId: Long) =
        service.deleteById(userId)

}