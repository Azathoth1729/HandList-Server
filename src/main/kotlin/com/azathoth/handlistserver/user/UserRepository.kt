package com.azathoth.handlistserver.user

import com.azathoth.handlistserver.user.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String): User?

//    fun findByIdOrNull(id: Long): User?
}