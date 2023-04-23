package com.azathoth.handlistserver.model.user

import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String): User?

//    fun findByIdOrNull(id: Long): User?
}