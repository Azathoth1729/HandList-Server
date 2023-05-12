package com.azathoth.handlistserver.user

import com.azathoth.handlistserver.task.TaskRepo
import com.azathoth.handlistserver.task.model.toDTO
import com.azathoth.handlistserver.user.model.User
import com.azathoth.handlistserver.user.model.UserDTO
import com.azathoth.handlistserver.user.model.toDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepo: UserRepo,
    private val taskRepo: TaskRepo
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails =
        userRepo.findByEmail(email) ?: throw UsernameNotFoundException("$email not found")

    fun getAll() = userRepo.findAll().map { it.toDTO() }

    fun getAllTasksByUserEmail(email: String) = taskRepo.findByAssignsEmail(email).map { it.toDTO() }


    fun update(userId: Long, user: UserDTO): User {
        val oldUser = userRepo.findByIdOrNull(userId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return oldUser.apply {
            user.nickname?.let { nickname = it }
            user.email?.let { email = it }
            user.role?.let { role = it }

            userRepo.save(oldUser)
        }
    }

    fun deleteById(userId: Long) =
        if (userRepo.existsById(userId)) userRepo.deleteById(userId)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)

}